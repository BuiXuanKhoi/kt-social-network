package com.example.socialnetworkproject.events;

import com.example.socialnetworkproject.models.entities.Users;
import org.springframework.context.ApplicationEvent;

public class AddDocumentEvent extends ApplicationEvent {
    private Users user;
    public AddDocumentEvent(Object source, Users user){
        super(source);
        this.user = user;
    }
    public Users getUser(){
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
