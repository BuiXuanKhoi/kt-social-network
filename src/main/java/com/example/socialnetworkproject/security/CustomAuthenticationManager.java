package com.example.socialnetworkproject.security;

import com.example.socialnetworkproject.exceptions.WrongCredentialException;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.repositories.UserRepository;
import com.example.socialnetworkproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public CustomAuthenticationManager(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        Optional<Users> optionalUsers = userRepository.findByEmail(email);

        if (!optionalUsers.isPresent()){
            throw new WrongCredentialException("Your email is not exist");
        }

        Users users = optionalUsers.get();
        String actualPassword = users.getPassword();

        if (!encoder.matches(password, actualPassword)){
            throw new WrongCredentialException("Your password is wrong");
        }

        String role = users.getRole().toString();
        Collection<GrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        return new EmailPasswordAuthentication(
                email,
                password,
                authorities
        );
    }
}
