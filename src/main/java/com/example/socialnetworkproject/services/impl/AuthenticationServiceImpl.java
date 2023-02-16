package com.example.socialnetworkproject.services.impl;

import com.example.socialnetworkproject.models.entities.DTO.SignUpRequest;
import com.example.socialnetworkproject.models.entities.Information;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.repositories.InformationRepository;
import com.example.socialnetworkproject.repositories.UserRepository;
import com.example.socialnetworkproject.services.AuthenticationService;
import com.example.socialnetworkproject.validation.Validator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;

    private InformationRepository informationRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private Validator validator;

    @Autowired
    public AuthenticationServiceImpl (UserRepository userRepository,InformationRepository informationRepository, BCryptPasswordEncoder passwordEncoder
                                    , Validator validator){
        this.userRepository = userRepository;
        this.informationRepository = informationRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public Users register(SignUpRequest request) {
        Users users = new Users();

        if(validator.isSignUpValid(request)){

            Information information = new Information();

            String passwordEncoded = passwordEncoder.encode(request.getPassword());

            request.setPassword(passwordEncoded);

            BeanUtils.copyProperties(request,users);

            BeanUtils.copyProperties(request,information);

            information.setUsers(users);

            informationRepository.save(information);
        }
        return users;
    }

}
