package com.viheakode.electronic_store.controller;

import com.viheakode.electronic_store.dto.request.LoginRequest;
import com.viheakode.electronic_store.service.serviceImp.JwtAuthenticationService;
import com.viheakode.electronic_store.util.ApiResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        String token = jwtAuthenticationService.authenticate(loginRequest);
        return ResponseEntity.ok().body(Map.of("token", token));
    }
}
