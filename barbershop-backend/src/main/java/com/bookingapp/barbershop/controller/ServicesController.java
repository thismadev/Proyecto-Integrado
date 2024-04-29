package com.bookingapp.barbershop.controller;

import com.bookingapp.barbershop.application.service.ServicesService;
import com.bookingapp.barbershop.domain.entity.Services;
import com.bookingapp.barbershop.response.ServicesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServicesController {

    private final ServicesService service;

    @PostMapping("/add")
    public ResponseEntity<ServicesResponse> addNewService(
            @RequestParam String serviceType,
            @RequestParam Integer price) {
        Services savedService = service.addNewService(serviceType, price);
        ServicesResponse response = new ServicesResponse(savedService.getId(), savedService.getServiceType(),
                savedService.getPrice());
        return ResponseEntity.ok(response);
    }
}
