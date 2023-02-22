package com.example.socialnetworkproject.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class EmailPasswordAuthentication extends AbstractAuthenticationToken {

    private Object email;
    private Object password;
    private Collection<? extends GrantedAuthority> roles;

    public EmailPasswordAuthentication(Object credential, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.email = principal;
        this.password = credential;
        this.roles = authorities;
    }

    public EmailPasswordAuthentication(Object credential,Object principal){
        super(null);
        this.email = principal;
        this.password = credential;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.email;
    }

}
