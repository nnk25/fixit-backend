package com.nikaru.fixit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.nikaru.fixit.domain.entity.User;
import com.nikaru.fixit.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends OidcUserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        logger.info("Google OIDC callback received for registration: {}", userRequest.getClientRegistration().getRegistrationId());

        OidcUser oidcUser = super.loadUser(userRequest);

        String email = oidcUser.getEmail();
        logger.info("Google OIDC user loaded; email present: {}", email != null && !email.isBlank());
        if (email == null || email.isBlank()) {
            throw new OAuth2AuthenticationException(
                new OAuth2Error("missing_email", "Google did not provide an email address", null));
        }

        User existingUser = userRepository.findByEmail(email).orElse(null);
        if (existingUser != null) {
            logger.info("Local user already exists with id: {}", existingUser.getId());
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            User savedUser = userRepository.saveAndFlush(newUser);
            logger.info("Local user created with id: {}", savedUser.getId());
        }

        return oidcUser;
    }
}