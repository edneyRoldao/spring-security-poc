package com.edntisolutions.aouth.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    String refreshToken(UserDetails userDetails, String token);

    String getAuthHeaderName();

}
