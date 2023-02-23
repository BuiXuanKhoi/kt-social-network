package com.example.socialnetworkproject.services.impl;

import com.example.socialnetworkproject.events.AddDocumentEvent;
import com.example.socialnetworkproject.events.SendEmailEvent;
import com.example.socialnetworkproject.exception.WrongInformationException;
import com.example.socialnetworkproject.models.entities.CustomUserDetails;
import com.example.socialnetworkproject.models.entities.DTO.request.LoginRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.SignUpRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.UpdatePasswordRequest;
import com.example.socialnetworkproject.models.entities.DTO.respond.LoginRespond;
import com.example.socialnetworkproject.models.entities.Information;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.repositories.UserRepository;
import com.example.socialnetworkproject.security.BaseTokenGenerator;
import com.example.socialnetworkproject.security.JwtAuthenticationFilter;
import com.example.socialnetworkproject.security.JwtTokenProvider;
import com.example.socialnetworkproject.services.AuthenticationService;
import com.example.socialnetworkproject.solr.SolrUserRepository;
import com.example.socialnetworkproject.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private Validator validator;

    private ApplicationEventPublisher eventPublisher;

    private JwtTokenProvider tokenProvider;

    private AuthenticationManager authenticationManager;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private SolrUserRepository solrUserRepository;

    private static final String secretCode = "kt-social-network ";



    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder
            , Validator validator, ApplicationEventPublisher eventPublisher, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager
            , JwtAuthenticationFilter jwtAuthenticationFilter, SolrUserRepository solrUserRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
        this.eventPublisher = eventPublisher;
        this.tokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.solrUserRepository = solrUserRepository;

    }

    @Override
    public void register(SignUpRequest request) {
        Users users = new Users();

        Information information = new Information();

        String passwordEncoded = passwordEncoder.encode(request.getPassword());

        request.setPassword(passwordEncoded);

        BeanUtils.copyProperties(request, users);

        BeanUtils.copyProperties(request, information);

        information.setUsers(users);

        userRepository.save(users);

        eventPublisher.publishEvent(new AddDocumentEvent(this, users));
    }

    public LoginRespond login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginRespond(jwt);
    }

    @Override
    public void resetPassword(String email, String userId) {
        String encryptUserId = secretCode + userId;
        String encryptString = BaseTokenGenerator.encrypt(encryptUserId);
        eventPublisher.publishEvent(new SendEmailEvent(this, email, encryptString));
    }

    @Transactional
    @Override
    public void changePassword(String encodedId, UpdatePasswordRequest passwordRequest) {
        String decryptString = BaseTokenGenerator.decrypt(encodedId);

        if (!decryptString.startsWith(secretCode)) {
            throw new WrongInformationException("This email address doesn't exist !!!");
        }
        UUID userId = UUID.fromString(decryptString.substring(18));
        String newPassword = passwordEncoder.encode(passwordRequest.getNewPassword());
        userRepository.updateEmail(newPassword,userId);
    }
}
