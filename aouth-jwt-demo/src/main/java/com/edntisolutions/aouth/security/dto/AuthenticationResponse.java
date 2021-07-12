package com.edntisolutions.aouth.security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode
public class AuthenticationResponse implements Serializable {

    @NotNull
    private String token;

}
