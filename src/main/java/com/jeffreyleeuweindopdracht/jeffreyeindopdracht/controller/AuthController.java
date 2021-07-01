package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.controller;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.request.LoginRequest;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.request.SignupRequest;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.response.JwtResponse;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.response.MessageResponse;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authorizationService.authenticateUser(loginRequest);
    }


    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
        return authorizationService.registerUser(signUpRequest);
    }

}
