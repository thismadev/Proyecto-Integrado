package com.bookingapp.barbershop.application.impl;

import com.bookingapp.barbershop.application.service.ServicesService;
import com.bookingapp.barbershop.domain.entity.Services;
import com.bookingapp.barbershop.domain.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;

    @Override
    public Services addNewService(String serviceType, Integer price) {
        Services service = new Services();
        service.setServiceType(serviceType);
        service.setPrice(price);

        return servicesRepository.save(service);
    }
}
