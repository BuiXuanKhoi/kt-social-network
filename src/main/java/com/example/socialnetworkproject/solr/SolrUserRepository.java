package com.example.socialnetworkproject.solr;

import com.example.socialnetworkproject.models.entities.document.UserDocument;
import org.apache.catalina.User;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface SolrUserRepository extends SolrCrudRepository<UserDocument, String> {
    @Query("email:*?0*")
    Optional<UserDocument> getUserDocumentByEmail(String email);

    @Query("id:*?0*")
    Optional<UserDocument> findUserDocumentByUserId(String userId);
}
