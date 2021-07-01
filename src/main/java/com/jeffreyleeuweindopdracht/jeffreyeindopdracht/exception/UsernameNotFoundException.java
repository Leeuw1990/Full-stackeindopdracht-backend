package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception;

public class UsernameNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String username) {
        super("Cannot find user " + username);
    }

}
