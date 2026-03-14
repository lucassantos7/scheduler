package com.maverick.scheduler.repository;

import com.maverick.scheduler.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByProviderIdAndDateTimeBetween(Long providerId, LocalDateTime start, LocalDateTime end);
}