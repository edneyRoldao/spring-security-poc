package com.edntisolutions.aouth.security.service;

import com.edntisolutions.aouth.security.dto.AuthenticationRequest;
import com.edntisolutions.aouth.security.dto.AuthenticationResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    AuthenticationResponse generateToken(AuthenticationRequest request);

    AuthenticationResponse refreshToken(HttpServletRequest request);

}
