package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.service;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.Repository.UserRepository;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        @Override
        @Transactional
        public UserDetails loadUserByUsername(String username)  {
            Users user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));
            return UserDetailsImpl.build(user);
        }
}
