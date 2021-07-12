package com.edntisolutions.aouth.security.converter;

import com.edntisolutions.aouth.security.dto.JwtUserDetails;
import com.edntisolutions.aouth.usermanager.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDetailsConverter implements Function<User, JwtUserDetails> {

    @Override
    public JwtUserDetails apply(User user) {
        return new JwtUserDetails(user.getPassword(), user.getUsername());
    }

}
