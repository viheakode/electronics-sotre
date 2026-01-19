package com.viheakode.electronic_store.service.serviceImp;

import com.viheakode.electronic_store.dto.request.UserRequest;
import com.viheakode.electronic_store.exception.BadRequestException;
import com.viheakode.electronic_store.model.Role;
import com.viheakode.electronic_store.model.User;
import com.viheakode.electronic_store.repository.RoleRepository;
import com.viheakode.electronic_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImp {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(UserRequest userRequest){

        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        for (String roleName : userRequest.getRoles()){
            Role role = roleRepository.findByRoleName(roleName);

            if (role == null){
                throw new BadRequestException("Role Not Found");
            }
            roles.add(role);
        }
        user.setRoles(roles);
        user.setPublisher(getPublisher());
        return userRepository.save(user);
    }


    private String getPublisher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }
}
