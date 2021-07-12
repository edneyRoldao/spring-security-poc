package com.edntisolutions.aouth.usermanager.converter;

import com.edntisolutions.aouth.usermanager.dto.UserRequestDTO;
import com.edntisolutions.aouth.usermanager.exception.UserPasswordException;
import com.edntisolutions.aouth.usermanager.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserConverter implements Function<UserRequestDTO, User> {

    @Override
    public User apply(UserRequestDTO request) {
        if (StringUtils.isBlank(request.getPassword()))
            throw new UserPasswordException("password is empty");

        return User
                .builder()
                .username(request.getUsername())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .build();
    }
}
