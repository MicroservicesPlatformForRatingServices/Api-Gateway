package com.gateway.ApiGateway.controller;

import com.gateway.ApiGateway.model.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient auth,
                                               @AuthenticationPrincipal OidcUser user, Model model){


            logger.info("into controller");

            LoginResponse login = new LoginResponse( user.getEmail(),
                    auth.getAccessToken().getTokenValue(),
                    auth.getRefreshToken().getTokenValue(),
                    auth.getAccessToken().getExpiresAt().atZone(ZoneId.systemDefault()).toLocalDate(),
                    user.getAuthorities()
            );

            return ResponseEntity.ok(login);
    }
}
