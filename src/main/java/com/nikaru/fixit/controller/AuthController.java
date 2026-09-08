package com.nikaru.fixit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikaru.fixit.domain.dto.AuthUserResponseDto;
import com.nikaru.fixit.domain.entity.User;
import com.nikaru.fixit.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<AuthUserResponseDto> me(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        User user = userService.getOrCreateUser(email);
        return ResponseEntity.ok(new AuthUserResponseDto(user.getId(), user.getEmail()));
    }
}
