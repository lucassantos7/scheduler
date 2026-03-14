package com.maverick.scheduler.controller;

import com.maverick.scheduler.dto.DailyScheduleDTO;
import com.maverick.scheduler.model.Appointment;
import com.maverick.scheduler.repository.AppointmentRepository;
import com.maverick.scheduler.repository.UserRepository;
import com.maverick.scheduler.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Appointment create(@Valid @RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Autowired
    private ScheduleService scheduleService;

    @Operation(summary = "Agenda do dia do prestador", description = "Retorna todos os compromissos de um profissional em uma data específica")
    @GetMapping("/daily/{providerId}")
    public List<DailyScheduleDTO> getDaily(@PathVariable Long providerId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return scheduleService.getDailySchedule(providerId, date);
    }
}