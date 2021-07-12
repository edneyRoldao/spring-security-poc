package com.edntisolutions.aouth.security.service.impl;

import com.edntisolutions.aouth.security.converter.UserDetailsConverter;
import com.edntisolutions.aouth.usermanager.model.User;
import com.edntisolutions.aouth.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service("appUserDetailsService")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final UserDetailsConverter userDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        return userDetailsConverter.apply(user);
    }

}
