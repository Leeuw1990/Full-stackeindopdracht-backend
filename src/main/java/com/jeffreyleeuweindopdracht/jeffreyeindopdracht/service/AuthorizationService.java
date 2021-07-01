package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.RoleRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.UserRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.ERole;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Role;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Users;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.request.LoginRequest;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.request.SignupRequest;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.response.JwtResponse;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.response.MessageResponse;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class AuthorizationService {

    // Autowired, om te kijken wat er gebeurt.
    private static final String ROLE_NOT_FOUND_ERROR = "Error: Role is not found.";

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }


    public ResponseEntity<MessageResponse> registerUser(@Valid SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Users user = new Users(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName());


        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND_ERROR));
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    public ResponseEntity<JwtResponse> authenticateUser(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

}
