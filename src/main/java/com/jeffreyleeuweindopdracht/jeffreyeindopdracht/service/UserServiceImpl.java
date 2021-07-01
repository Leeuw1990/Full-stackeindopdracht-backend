package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.UserRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.NotAuthorizedException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.RecordNotFoundException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Users;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public Collection<Users> getAllUsers() {
        Collection<Users> users = userRepository.findAll();
        if (users.isEmpty()) throw new RecordNotFoundException();
        return users;
    }

    @Override
    public Users getUserByUsername(String username) {
        if (!userRepository.existsByUsername(username)) throw new RecordNotFoundException();
        return userRepository.findByUsername(username).get();
    }

    @Override
    public Map<String, String> getUserDetailsByUsername(String username) {
        if (!userRepository.existsByUsername(username)) throw new RecordNotFoundException();

        Users user = userRepository.findByUsername(username).get();
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("username", user.getUsername());
        userDetails.put("firstName", user.getFirstName());
        userDetails.put("lastName", user.getLastName());
        userDetails.put("email", user.getEmail());
        return userDetails;
    }

    @Override
    public void updateUserByUsername(String username, Principal principal, UpdateUserRequest userRequest) {
        if (!userRepository.existsByUsername(username))
            throw new RecordNotFoundException("User " + username + " does not exist.");
        Users user = userRepository.findByUsername(username).get();

        try {
            if (!userRequest.getPassword().isEmpty() && !userRequest.getPasswordConfirmation().isEmpty()) {
                user.setPassword(encoder.encode(userRequest.getPassword()));
            }
        } catch (NullPointerException e) {
            user.setPassword(user.getPassword());
        }

        if (!userRequest.getEmail().isEmpty()) {
            user.setEmail(userRequest.getEmail());
        }
        if (!userRequest.getFirstName().isEmpty()) {
            user.setFirstName(userRequest.getFirstName());
        }
        if (!userRequest.getLastName().isEmpty()) {
            user.setLastName(userRequest.getLastName());
        }
        userRepository.save(user);
    }

    @Override
    public Users getCurrentUser(Principal principal) {
        if (principal.getName() == null) throw new NotAuthorizedException();
        return getUserByUsername(principal.getName());
    }
}
