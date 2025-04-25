package com.jetbrains.aplicatiequiz.controllers;

import com.jetbrains.aplicatiequiz.enums.Role;
import com.jetbrains.aplicatiequiz.models.User;
import com.jetbrains.aplicatiequiz.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setRole(Role.USER);
        User created = userService.registerUser(user);
        return ResponseEntity.ok(created);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePersonalAccount(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUserByUsername(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
