package com.maverick.scheduler.dto;

import java.time.LocalTime;

public record DailyScheduleDTO(
        Long id,
        LocalTime time,
        String clientName,
        String clientPhone,
        String serviceName,
        Integer duration
) {
}