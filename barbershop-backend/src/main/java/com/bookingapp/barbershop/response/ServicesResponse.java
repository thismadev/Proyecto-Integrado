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
}
