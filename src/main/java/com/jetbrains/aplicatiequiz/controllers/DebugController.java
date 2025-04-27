package com.jetbrains.aplicatiequiz.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {
    @GetMapping("/debug/me")
    public String debugMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "User: " + auth.getName() + ", Authorities: " + auth.getAuthorities();
    }
}
