package com.jeffreyleeuweindopdracht.jeffreyeindopdracht;


import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Users;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.payload.request.SignupRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUser {
    private static final Logger logger =  LoggerFactory.getLogger(TestUser.class);
    @Test
    public void testUser(){
            SignupRequest signUpRequest = new SignupRequest();
            signUpRequest.setUsername("Username");
            signUpRequest.setEmail("email@emial.nl");
            signUpRequest.setPassword("password");
            signUpRequest.setFirstName("firstname");
            signUpRequest.setLastName("Lastname");



        Users user = new Users(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName()


//                encoder.encode(signUpRequest.getPassword()));
        );

        logger.info("user" + user.toString());
    }
}
