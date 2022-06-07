package com.example.orderManagement.security;

import com.example.orderManagement.entity.User;
import com.example.orderManagement.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), getAuth());
    }

    private List<SimpleGrantedAuthority> getAuth() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}
