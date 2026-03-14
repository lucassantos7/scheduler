package com.maverick.scheduler.service;

import com.maverick.scheduler.dto.DailyScheduleDTO;
import com.maverick.scheduler.model.Appointment;
import com.maverick.scheduler.model.WorkConfig;
import com.maverick.scheduler.repository.AppointmentRepository;
import com.maverick.scheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<DailyScheduleDTO> getDailySchedule(Long providerId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        return appointmentRepository.findAllByProviderIdAndDateTimeBetween(providerId, start, end)
                .stream()
                .map(app -> new DailyScheduleDTO(
                        app.getId(),
                        app.getDateTime().toLocalTime(),
                        app.getClientFirstName() + " " + app.getClientLastName(),
                        app.getClientPhone(),
                        (app.getService() != null) ? app.getService().getName() : "Serviço não definido",
                        (app.getService() != null) ? app.getService().getDuration() : 0
                ))
                .sorted(Comparator.comparing(DailyScheduleDTO::time)) // Ordena por hora
                .toList();
    }

    public List<LocalTime> getAvailableSlots(Long userId, LocalDate date) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        WorkConfig config = user.getWorkConfig();

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        List<Appointment> existingAppointments = appointmentRepository
                .findAllByProviderIdAndDateTimeBetween(userId, startOfDay, endOfDay);

        List<LocalTime> busySlots = existingAppointments.stream()
                .map(app -> app.getDateTime().toLocalTime())
                .toList();

        List<LocalTime> availableSlots = new ArrayList<>();
        LocalTime currentSlot = config.getStartTime();

        while (currentSlot.isBefore(config.getEndTime())) {
            boolean isLunch = !currentSlot.isBefore(config.getLunchStart()) && currentSlot.isBefore(config.getLunchEnd());

            boolean isBusy = busySlots.contains(currentSlot);

            if (!isLunch && !isBusy) {
                availableSlots.add(currentSlot);
            }
            currentSlot = currentSlot.plusMinutes(config.getSlotDuration());
        }

        return availableSlots;
    }
}