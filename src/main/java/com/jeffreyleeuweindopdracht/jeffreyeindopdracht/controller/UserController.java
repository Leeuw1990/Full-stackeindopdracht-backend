package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.controller;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.request.UpdateUserRequest;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.response.MessageResponse;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allusers")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> userAccess() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<Object> getUser(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @GetMapping("/user/{username}/details")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<Object> getUserDetails(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getUserDetailsByUsername(username));
    }

    @PatchMapping("/user/{username}/details/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> updateUser(@PathVariable String username, Principal principal, @RequestBody UpdateUserRequest updateRequest) {
        userService.updateUserByUsername(username, principal, updateRequest);
        return ResponseEntity.ok().body(new MessageResponse("User details updated successfully!"));
    }
}


