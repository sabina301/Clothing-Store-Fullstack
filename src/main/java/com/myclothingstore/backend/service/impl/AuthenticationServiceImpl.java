package com.myclothingstore.backend.service.impl;

import com.myclothingstore.backend.entity.CartEntity;
import com.myclothingstore.backend.exception.RoleNotFoundException;
import com.myclothingstore.backend.exception.UserNotFoundException;
import com.myclothingstore.backend.model.DTO.LoginResponseDTO;
import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.model.DTO.RegisterResponseDTO;
import com.myclothingstore.backend.model.Role;
import com.myclothingstore.backend.repository.CartRepository;
import com.myclothingstore.backend.repository.RoleRepository;
import com.myclothingstore.backend.repository.UserRepository;
import com.myclothingstore.backend.service.AuthenticationService;
import com.myclothingstore.backend.service.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
public class AuthenticationServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    public RegisterResponseDTO registerUser(String username, String password){
        if (userRepository.findByUsername(username).isEmpty()){
            String encodedPassword = passwordEncoder.encode(password);
            Role userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new RoleNotFoundException("Role is not found"));
            Set<Role> authorities = new HashSet<Role>();
            authorities.add(userRole);

            UserEntity userEntity = new UserEntity(username, encodedPassword, authorities);
            userRepository.save(userEntity);
            return new RegisterResponseDTO(username);
        } else {
            return new RegisterResponseDTO(null);
        }
    }


    public LoginResponseDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User is not found")), token);

        } catch(Exception e){
            return new LoginResponseDTO(null, "");
        }
    }
}
