package com.maverick.scheduler.repository;

import com.maverick.scheduler.model.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOfferRepository extends JpaRepository<ServiceOffer, Long> {
}