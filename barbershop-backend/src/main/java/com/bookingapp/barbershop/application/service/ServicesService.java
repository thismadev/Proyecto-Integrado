package com.bookingapp.barbershop.application.service;

import com.bookingapp.barbershop.domain.entity.Services;

import java.util.List;

public interface ServicesService {
    Services addNewService(String serviceType, Integer price);
    List<String> getAllServiceType();
    List<Services> getAllServices();
    void deleteService(Long serviceId);
}
