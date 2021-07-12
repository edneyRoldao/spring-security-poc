package com.edntisolutions.aouth.usermanager.controller;

import com.edntisolutions.aouth.usermanager.dto.UserRequestDTO;
import com.edntisolutions.aouth.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class UserController {

    private final UserService userService;

    @PostMapping("create")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequestDTO request) {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("user has been created");
    }

}
