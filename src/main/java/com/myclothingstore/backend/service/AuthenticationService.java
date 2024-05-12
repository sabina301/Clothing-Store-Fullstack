package com.myclothingstore.backend.service;

import com.myclothingstore.backend.model.DTO.LoginResponseDTO;
import com.myclothingstore.backend.model.DTO.UserProfileDTO;

import java.security.Principal;

public interface AuthenticationService {
    void registerUser(String username, String password) throws Exception;
    LoginResponseDTO loginUser(String username, String password) throws Exception;
    UserProfileDTO getUser(Principal principal) throws Exception;
}


