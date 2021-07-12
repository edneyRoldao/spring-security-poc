package com.edntisolutions.aouth.security.controller;

import com.edntisolutions.aouth.security.dto.AuthenticationRequest;
import com.edntisolutions.aouth.security.dto.AuthenticationResponse;
import com.edntisolutions.aouth.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("generate-token")
    public ResponseEntity<AuthenticationResponse> generateToken(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.generateToken(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
        AuthenticationResponse response = authenticationService.refreshToken(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
