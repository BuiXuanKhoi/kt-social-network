package com.example.socialnetworkproject.services.impl;

import com.example.socialnetworkproject.constants.Role;
import com.example.socialnetworkproject.models.entities.DTO.responds.LoginRespond;
import com.example.socialnetworkproject.models.entities.DTO.request.LoginRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.SignUpRequest;
import com.example.socialnetworkproject.models.entities.Information;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.repositories.InformationRepository;
import com.example.socialnetworkproject.repositories.UserRepository;
import com.example.socialnetworkproject.security.jwt.JwtUtils;
import com.example.socialnetworkproject.services.AuthenticationService;
import com.example.socialnetworkproject.services.UserService;
import com.example.socialnetworkproject.validation.Validator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;

    private InformationRepository informationRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private Validator validator;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl (UserRepository userRepository,InformationRepository informationRepository, BCryptPasswordEncoder passwordEncoder
                                    , Validator validator, UserService userService, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.informationRepository = informationRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Users register(SignUpRequest request) {

        if (!validator.isSignUpValid(request)){
            return null;
        }

        String rawPassword = request.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        request.setPassword(encodedPassword);

        Users users = new Users();
        BeanUtils.copyProperties(request, users);

        Information information = new Information();
        BeanUtils.copyProperties(request, information);

        String expectRole = request.getRole();
        Role role = Role.of(expectRole);
        users.setRole(role);

        users.setInformation(information);

        return userRepository.save(users);
    }

    @Override
    public LoginRespond login(LoginRequest request) {
        String loginUserName = request.getUserName();
        String loginPassword = request.getPassword();

        Users users = userRepository.findByUserName(loginUserName)
                                    .orElseThrow(
                                            () -> new BadCredentialsException("Your password or username is wrong, please check again")
                                    );

        Role role = users.getRole();
        Collection<GrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserName,
                        loginPassword,
                        authorities
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = JwtUtils.generateByUserName(loginUserName);

        return LoginRespond.builder()
                           .refreshToken("not have yet")
                           .timestamp(LocalDateTime.now())
                           .userName(loginUserName)
                           .jwt(jwtToken)
                           .build();
    }

}
