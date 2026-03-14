package com.maverick.scheduler.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "work_configs")
@Getter
@Setter
public class WorkConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime lunchStart;
    private LocalTime lunchEnd;
    private Integer slotDuration;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}