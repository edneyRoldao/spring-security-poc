package com.edntisolutions.aouth.usermanager.service.impl;

import com.edntisolutions.aouth.usermanager.converter.UserConverter;
import com.edntisolutions.aouth.usermanager.dto.UserRequestDTO;
import com.edntisolutions.aouth.usermanager.exception.UserAlreadyExistsException;
import com.edntisolutions.aouth.usermanager.exception.UserNotFoundException;
import com.edntisolutions.aouth.usermanager.model.User;
import com.edntisolutions.aouth.usermanager.repository.UserRepository;
import com.edntisolutions.aouth.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserConverter userConverter;

    @Override
    public void createUser(UserRequestDTO userRequestDTO) {
        if (userExists(userRequestDTO.getUsername()))
            throw new UserAlreadyExistsException();

        User user = userConverter.apply(userRequestDTO);

        repository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean userExists(String username) {
        return repository.existsByUsername(username);
    }

}
