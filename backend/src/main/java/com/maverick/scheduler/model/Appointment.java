package com.maverick.scheduler.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String clientFirstName;

    @NotBlank(message = "O sobrenome do cliente é obrigatório")
    private String clientLastName;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Telefone inválido")
    private String clientPhone;

    @NotNull(message = "A data e hora são obrigatórias")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceOffer service;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;
}