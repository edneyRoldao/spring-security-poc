package com.edntisolutions.aouth.usermanager.service;

import com.edntisolutions.aouth.usermanager.dto.UserRequestDTO;
import com.edntisolutions.aouth.usermanager.model.User;

public interface UserService {

    void createUser(UserRequestDTO userRequestDTO);

    User getUserByUsername(String username);

    boolean userExists(String username);

}
