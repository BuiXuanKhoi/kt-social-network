package com.example.socialnetworkproject.events;

import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.models.entities.document.UserDocument;
import com.example.socialnetworkproject.solr.SolrUserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SolrEventListener {

    @Resource
    private SolrUserRepository solrUserRepository;

    @Async
    @EventListener
    public void indexUsers(AddDocumentEvent event) {
        UserDocument userDocument = new UserDocument();
        Users user = event.getUser();

        userDocument.setUserID(user.getUserId().toString());
        userDocument.setUserName(user.getUserName());
        userDocument.setEmail(user.getEmail());

        solrUserRepository.save(userDocument);
    }
}
