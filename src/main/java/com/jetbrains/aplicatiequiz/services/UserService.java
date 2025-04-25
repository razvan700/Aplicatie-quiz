package com.jetbrains.aplicatiequiz.services;

import com.jetbrains.aplicatiequiz.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerUser(User user);
}
