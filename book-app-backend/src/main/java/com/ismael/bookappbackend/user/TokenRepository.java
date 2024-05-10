package com.ismael.bookappbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    /*
     * El Optional permite manejar de manera más segura la posibilidad de no encontrar el token,
     * evitando así el riesgo de NullPointerException.
     */
    Optional<Token> findByToken(String token);
}
