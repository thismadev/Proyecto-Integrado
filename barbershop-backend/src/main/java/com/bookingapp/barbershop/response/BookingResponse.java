package com.bookingapp.barbershop.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Long bookingId;

    private LocalDate bookingDate;

    private String customerFullName;

    private String customerEmail;

    private String bookingConfirmationCode;

    private ServicesResponse service;

    public BookingResponse(Long bookingId, LocalDate bookingDate, String bookingConfirmationCode) {
    }
}
