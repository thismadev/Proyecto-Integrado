package com.bookingapp.barbershop.application.service;

import com.bookingapp.barbershop.domain.entity.Services;

import java.util.List;
import java.util.Optional;

public interface ServicesService {
    Services addNewService(String serviceType, Integer price);

    List<String> getAllServiceType();

    List<Services> getAllServices();

    void deleteService(Integer serviceId);

    Services updateService(Integer serviceId, String serviceType, Integer price);

    Optional<Services> getServiceById(Integer serviceId);
}
