package com.myclothingstore.backend.service.impl;

import com.myclothingstore.backend.repository.UserRepository;
import com.myclothingstore.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//
//@Service
//
//public class UserServiceImpl implements UserDetailsService, UserService {
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
//    }
//}