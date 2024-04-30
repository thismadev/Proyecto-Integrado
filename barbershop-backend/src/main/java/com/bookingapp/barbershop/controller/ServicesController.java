package com.bookingapp.barbershop.controller;

import com.bookingapp.barbershop.application.impl.BookedServiceServiceImpl;
import com.bookingapp.barbershop.application.service.ServicesService;
import com.bookingapp.barbershop.domain.entity.BookedService;
import com.bookingapp.barbershop.domain.entity.Services;
import com.bookingapp.barbershop.response.BookingResponse;
import com.bookingapp.barbershop.response.ServicesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServicesController {

    private final ServicesService service;
    private final BookedServiceServiceImpl bookedService;

    @PostMapping("/add")
    public ResponseEntity<ServicesResponse> addNewService(
            @RequestParam String serviceType,
            @RequestParam Integer price) {
        Services savedService = service.addNewService(serviceType, price);
        ServicesResponse response = new ServicesResponse(savedService.getId(), savedService.getServiceType(),
                savedService.getPrice());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/service-type")
    public List<String> getServiceType() {
        return service.getAllServiceType();
    }

    public ResponseEntity<List<ServicesResponse>> getAllServices() {
        List<Services> services = service.getAllServices();
        List<ServicesResponse> servicesResponses = new ArrayList<>();
        for(Services service : services) {
            ServicesResponse servicesResponse = getServiceResponse(service);
            servicesResponses.add(servicesResponse);
        }
        return ResponseEntity.ok(servicesResponses);
    }

    private ServicesResponse getServiceResponse(Services service) {
        List<BookedService> bookings = getAllBookingsByServiceId(service.getId());
        List<BookingResponse> bookingInfo = bookings
                .stream()
                .map(booking -> new BookingResponse(booking.getBookingId(),
                        booking.getBookingDate(),
                        booking.getBookingConfirmationCode())).toList();
        return new ServicesResponse(service.getId(),
                service.getServiceType(),
                service.getPrice(),
                service.getIsBooked(), bookingInfo);
    }

    private List<BookedService> getAllBookingsByServiceId(Long id) {
        return bookedService.getAllBookingsByServiceId(id);
    }
}
