package com.bookingapp.barbershop.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_type")
    private String serviceType;

    private Integer price;

    @Column(name = "is_booked")
    private Boolean isBooked = false;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookedService> bookings;

    public void addBooking(BookedService booking) {
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
            booking.setService(this);
            isBooked = true;
            String bookingCode = RandomStringUtils.randomNumeric(10);
            booking.setBookingConfirmationCode(bookingCode);
    }
}
