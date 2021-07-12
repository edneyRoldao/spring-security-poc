package com.edntisolutions.aouth.usermanager.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserRequestDTO {

    @NotNull
    private String username;

    private String password;

}
