package com.bookingapp.barbershop.application.service;

import com.bookingapp.barbershop.domain.entity.Services;

public interface ServicesService {
    Services addNewService(String serviceType, Integer price);
}
