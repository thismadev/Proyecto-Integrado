package com.bookingapp.barbershop.controller;

import com.bookingapp.barbershop.application.impl.BookedServiceServiceImpl;
import com.bookingapp.barbershop.application.service.ServicesService;
import com.bookingapp.barbershop.domain.entity.BookedService;
import com.bookingapp.barbershop.domain.entity.Services;
import com.bookingapp.barbershop.exception.ResourceNotFoundException;
import com.bookingapp.barbershop.response.BookingResponse;
import com.bookingapp.barbershop.response.ServicesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServicesController {

    private final ServicesService service;
    private final BookedServiceServiceImpl bookedService;

    @PostMapping("/add")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ServicesResponse> addNewService(
            @RequestParam String serviceType,
            @RequestParam Integer price) {
        Services savedService = service.addNewService(serviceType, price);
        ServicesResponse response = new ServicesResponse(savedService.getId(), savedService.getServiceType(),
                savedService.getPrice());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/service-type")
    public List<String> getServiceTypes() {
        return service.getAllServiceType();
    }

    @GetMapping("/all-services")
    public ResponseEntity<List<ServicesResponse>> getAllServices() throws SQLException {
        List<Services> services = service.getAllServices();
        List<ServicesResponse> servicesResponse = new ArrayList<>();
        for (Services service : services) {
            ServicesResponse serviceResponse = getServiceResponse(service);
            servicesResponse.add(serviceResponse);
        }
        return ResponseEntity.ok(servicesResponse);
    }

    @DeleteMapping("/delete/service/{serviceId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteService(@PathVariable("serviceId") Long serviceId) {
        service.deleteService(serviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{serviceId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ServicesResponse> updateService(@PathVariable Integer serviceId,
                                                   @RequestParam(required = false)  String serviceType,
                                                   @RequestParam(required = false) Integer price) {

        Services theService = service.updateService(serviceId, serviceType, price);
        ServicesResponse servicesResponse = getServiceResponse(theService);
        return ResponseEntity.ok(servicesResponse);
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<Optional<ServicesResponse>> getServiceById(@PathVariable Integer serviceId){
        Optional<Services> theService = service.getServiceById(serviceId);
        return theService.map(services -> {
            ServicesResponse servicesResponse = getServiceResponse(services);
            return  ResponseEntity.ok(Optional.of(servicesResponse));
        }).orElseThrow(() -> new ResourceNotFoundException("Service not found"));
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
