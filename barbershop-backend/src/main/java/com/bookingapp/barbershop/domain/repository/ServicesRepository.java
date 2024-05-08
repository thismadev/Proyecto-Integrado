package com.bookingapp.barbershop.domain.repository;

import com.bookingapp.barbershop.domain.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
    @Query("SELECT DISTINCT s.serviceType FROM Services s")
    List<String> findDistinctServiceType();
}
