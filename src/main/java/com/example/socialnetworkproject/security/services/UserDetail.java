package com.example.socialnetworkproject.security.services;

import com.example.socialnetworkproject.constants.Role;
import com.example.socialnetworkproject.models.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class UserDetail implements UserDetails {

    private String userName;
    private String password;
    private Collection<GrantedAuthority> roles;

    public UserDetail(String userName, String password, Collection<GrantedAuthority> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public static UserDetails init(Users users){
        List<GrantedAuthority> authorities = new LinkedList<>();
        Role userRole = users.getRole();
        authorities.add(new SimpleGrantedAuthority(userRole.toString()));
        return new UserDetail(
                users.getUserName(),
                users.getPassword(),
                authorities
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
