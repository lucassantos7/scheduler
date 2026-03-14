package com.maverick.scheduler.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "services")
@Getter
@Setter
public class ServiceOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Ex: "Corte de Cabelo"
    private String description;
    private Double price;
    private Integer duration;   // em minutos
}