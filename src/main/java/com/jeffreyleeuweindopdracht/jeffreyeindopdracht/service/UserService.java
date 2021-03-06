package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Users;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.request.UpdateUserRequest;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

public interface UserService {
    Collection<Users> getAllUsers();
    Users getUserByUsername(String username);
    void updateUserByUsername(String username, Principal principal, UpdateUserRequest userRequest);
    Map<String, String> getUserDetailsByUsername(String username);
}
