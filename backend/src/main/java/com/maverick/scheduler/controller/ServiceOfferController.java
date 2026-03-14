package com.maverick.scheduler.controller;

import com.maverick.scheduler.model.ServiceOffer;
import com.maverick.scheduler.repository.ServiceOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceOfferController {

    @Autowired
    private ServiceOfferRepository serviceOfferRepository;

    @GetMapping
    public List<ServiceOffer> listAll() {
        return serviceOfferRepository.findAll();
    }

    @PostMapping
    public ServiceOffer create(@RequestBody ServiceOffer service) {
        return serviceOfferRepository.save(service);
    }
}