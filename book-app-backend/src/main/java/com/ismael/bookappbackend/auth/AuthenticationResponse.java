package com.ismael.bookappbackend.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class AuthenticationResponse {

    private String token;



}
