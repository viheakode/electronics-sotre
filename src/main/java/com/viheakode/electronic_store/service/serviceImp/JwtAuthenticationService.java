package com.viheakode.electronic_store.service.serviceImp;

import com.viheakode.electronic_store.dto.request.LoginRequest;
import com.viheakode.electronic_store.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private MoyJwtService moyJwtService;

    public String authenticate(LoginRequest loginRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            log.warn("Incorrect Username & Password");
            throw new RuntimeException(e);
        }
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        return moyJwtService.generateToken(userDetails);
    }
}
