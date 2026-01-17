package com.viheakode.electronic_store.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
