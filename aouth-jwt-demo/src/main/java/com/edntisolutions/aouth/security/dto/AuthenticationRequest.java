package com.edntisolutions.aouth.security.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode
public class AuthenticationRequest implements Serializable {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
