package com.gateway.ApiGateway.model;


import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;


public class LoginResponse {

    private String userName;
    private String accessToken;
    private String refreshToken;
    private LocalDate expiredAt;
    private Collection<? extends GrantedAuthority> authorities;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LocalDate getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDate expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public LoginResponse(String userName, String accessToken, String refreshToken, LocalDate expiredAt, Collection<? extends GrantedAuthority> authorities) {
        this.userName = userName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiredAt = expiredAt;
        this.authorities = authorities;
    }

    public LoginResponse() {
    }
}
