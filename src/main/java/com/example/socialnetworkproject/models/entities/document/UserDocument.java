package com.example.socialnetworkproject.models.entities.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;
import javax.persistence.Index;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@SolrDocument(solrCoreName = "user")
@NoArgsConstructor
public class UserDocument {

    @Indexed(name = "id",type = "string")
    @Id
    private String userID;


    @Indexed(name = "userName",type = "string")
    private String userName;

    @Indexed(name = "email" , type = "string")
    private String email;

}
