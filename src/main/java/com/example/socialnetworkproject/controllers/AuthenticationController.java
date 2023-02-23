package com.example.socialnetworkproject.controllers;

import com.example.socialnetworkproject.exception.WrongInformationException;
import com.example.socialnetworkproject.models.entities.DTO.request.PasswordResetRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.LoginRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.SignUpRequest;
import com.example.socialnetworkproject.models.entities.DTO.request.UpdatePasswordRequest;
import com.example.socialnetworkproject.models.entities.DTO.respond.LoginRespond;
import com.example.socialnetworkproject.models.entities.DTO.respond.SuccessMessage;
import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.models.entities.document.UserDocument;
import com.example.socialnetworkproject.repositories.UserRepository;
import com.example.socialnetworkproject.services.AuthenticationService;
import com.example.socialnetworkproject.solr.SolrUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private SolrUserRepository solrUserRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<SuccessMessage> signUp(@RequestBody SignUpRequest request){
        Optional<UserDocument> userOptional = solrUserRepository.getUserDocumentByEmail(request.getEmail());

        if(!userOptional.isEmpty()){
            throw new WrongInformationException("The email  " + request.getEmail() +" already exists !!!");
        }

        authenticationService.register(request);
        return  ResponseEntity.ok(new SuccessMessage("User account has been registered!"));
    }

    @PostMapping(value = "/login")
    public LoginRespond login(@Valid @RequestBody LoginRequest request){
        return authenticationService.login(request);
    }

    @PostMapping(value = "/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordResetRequest request){
        Optional<Users> user = userRepository.findByEmail(request.getEmail());
        if(user.isEmpty()){
            throw new WrongInformationException("Account with email " + request.getEmail() + " doesn't exist !!!");
        }
        authenticationService.resetPassword(request.getEmail(),user.get().getUserId().toString());
        return ResponseEntity.ok().body(new SuccessMessage("Password reset email sent "));
    }

    @PatchMapping(value = "/forgot-password/reset/{id}")
    public SuccessMessage updatePassword(@PathVariable("id") String encodedId, @RequestBody UpdatePasswordRequest passwordRequest){
        authenticationService.changePassword(encodedId,passwordRequest);
        return new SuccessMessage("Password has been updated !!!");
    }

    @PostMapping(value = "/deleteSolr")
    @Transactional
    public ResponseEntity<?> deleteSolr(){
        // Use this to clear solr document just for local
        solrUserRepository.deleteAll();
        return ResponseEntity.ok().body(new SuccessMessage("Solr delete !!!"));
    }
}

