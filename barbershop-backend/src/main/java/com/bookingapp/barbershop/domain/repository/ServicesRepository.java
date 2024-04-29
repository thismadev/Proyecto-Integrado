package com.bookingapp.barbershop.domain.repository;

import com.bookingapp.barbershop.domain.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Services, Long> {
}
