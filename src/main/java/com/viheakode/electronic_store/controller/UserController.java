package com.viheakode.electronic_store.controller;

import com.viheakode.electronic_store.dto.request.UserRequest;
import com.viheakode.electronic_store.model.User;
import com.viheakode.electronic_store.service.serviceImp.UserServiceImp;
import com.viheakode.electronic_store.util.ApiResponseStructure;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest, HttpServletRequest request){
        User user = userServiceImp.createUser(userRequest);
        return ApiResponseStructure.response(true, 201, "Created", user, request.getRequestURI(), HttpStatus.CREATED);
    }
}
