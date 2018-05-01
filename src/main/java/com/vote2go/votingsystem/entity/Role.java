package com.vote2go.votingsystem.entity;
import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {

    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
