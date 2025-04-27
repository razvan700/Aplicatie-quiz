package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.enums.Role;
import com.jetbrains.aplicatiequiz.models.Attempt;
import com.jetbrains.aplicatiequiz.models.User;

import java.util.List;

public class UserDTO {

    private String password;
    private Long id;

    private String username;

    private Role Role;




    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public com.jetbrains.aplicatiequiz.enums.Role getRole() {
        return Role;
    }

    public void setRole(com.jetbrains.aplicatiequiz.enums.Role role) {
        Role = role;
    }



    public UserDTO(User user){

        this.id = user.getId();

        this.Role = user.getRole();

        this.username = user.getUsername();

        this.password = user.getPassword();

    }

}
