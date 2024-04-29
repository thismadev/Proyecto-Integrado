package com.bookingapp.barbershop.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesResponse {
    private Long id;

    private String serviceType;

    private Integer price;

    private Boolean isBooked = false;

    private List<BookingResponse> bookings;

    public ServicesResponse(Long id, String serviceType, Integer price) {
        this.id = id;
        this.serviceType = serviceType;
        this.price = price;
    }
}
