package com.bookingapp.barbershop.application.impl;

import com.bookingapp.barbershop.application.service.ServicesService;
import com.bookingapp.barbershop.domain.entity.Services;
import com.bookingapp.barbershop.domain.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    @Override
    public List<String> getAllServiceType() {
        return servicesRepository.findDistinctServiceType();
    }

    @Override
    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public void deleteService(Integer serviceId) {
        Optional<Services> theService = servicesRepository.findById(serviceId);
        if(theService.isPresent()){
            servicesRepository.deleteById(serviceId);
        }
    }

    @Override
    public Services updateService(Integer serviceId, String serviceType, Integer price) {
        Services services = servicesRepository.findById(serviceId).get();
        if (serviceType != null) services.setServiceType(serviceType);
        if (price != null) services.setPrice(price);

        return servicesRepository.save(services);
    }

    @Override
    public Optional<Services> getServiceById(Integer serviceId) {
        return Optional.of(servicesRepository.findById(serviceId).get());
    }

}
