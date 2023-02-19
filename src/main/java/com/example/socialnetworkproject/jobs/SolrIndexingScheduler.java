package com.example.socialnetworkproject.jobs;

import com.example.socialnetworkproject.models.entities.Users;
import com.example.socialnetworkproject.models.entities.document.UserDocument;
import com.example.socialnetworkproject.repositories.UserRepository;
import com.example.socialnetworkproject.solr.SolrUserRepository;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SolrIndexingScheduler {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private SolrUserRepository solrUserRepository;

    @Scheduled(cron = "0 */1 * * * *")
    @Transactional
    @Async
    public void indexUsers() {
            List<Users> usersList = userRepository.findAll();
            List<UserDocument> documents = new ArrayList<>();

            for (Users user : usersList) {
                UserDocument doc = new UserDocument();
                doc.setUserName(user.getUserName());
                doc.setEmail(user.getEmail());
                documents.add(doc);
            }

            solrUserRepository.saveAll(documents);
    }
}
