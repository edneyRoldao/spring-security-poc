package com.edntisolutions.aouth.security.service.impl;

import com.edntisolutions.aouth.security.dto.AuthenticationRequest;
import com.edntisolutions.aouth.security.dto.AuthenticationResponse;
import com.edntisolutions.aouth.security.exception.AppUnauthorizedException;
import com.edntisolutions.aouth.security.service.AuthenticationService;
import com.edntisolutions.aouth.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    @Qualifier("appUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse generateToken(AuthenticationRequest request) {
        try {
            UsernamePasswordAuthenticationToken user =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            Authentication authentication = authenticationManager.authenticate(user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtService.generateToken(userDetails);

            return AuthenticationResponse.builder().token(token).build();

        } catch (Exception e) {
            throw new AppUnauthorizedException(e.getMessage());
        }
    }

    @Override
    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        try {
            String token = request.getHeader(jwtService.getAuthHeaderName());
            String username = jwtService.extractUsername(token);
            UserDetails user = userDetailsService.loadUserByUsername(username);
            String refreshedToken = jwtService.refreshToken(user, token);

            return AuthenticationResponse.builder().token(refreshedToken).build();

        } catch (Exception e) {
            throw new AppUnauthorizedException(e.getMessage());
        }
    }

}
