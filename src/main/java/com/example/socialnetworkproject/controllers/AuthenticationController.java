package com.example.socialnetworkproject.controllers;

import com.example.socialnetworkproject.exception.WrongInformationException;
import com.example.socialnetworkproject.jobs.SolrIndexingScheduler;
import com.example.socialnetworkproject.models.entities.DTO.request.SignUpRequest;
import com.example.socialnetworkproject.models.entities.DTO.reply.SuccessMessage;
import com.example.socialnetworkproject.models.entities.document.UserDocument;
import com.example.socialnetworkproject.services.AuthenticationService;
import com.example.socialnetworkproject.solr.SolrUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private SolrUserRepository solrUserRepository;

    @Autowired
    private SolrIndexingScheduler solrIndexingScheduler;

    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<SuccessMessage> signUp(@RequestBody SignUpRequest request){

        Optional<UserDocument> userOptional = solrUserRepository.findUserDocumentByEmail(request.getEmail());

        if(userOptional.isPresent()){
            throw new WrongInformationException("The email  " + request.getEmail() +" already exists !!!");
        }

        if(!request.getEmail().contains("@")){
            throw new WrongInformationException("Wrong email format !!!");
        }

        authenticationService.register(request);
        return  ResponseEntity.ok(new SuccessMessage("User account has been registered!"));
    }

}
