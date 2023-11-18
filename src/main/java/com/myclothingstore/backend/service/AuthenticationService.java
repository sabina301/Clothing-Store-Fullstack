package com.myclothingstore.backend.service;

import com.myclothingstore.backend.model.DTO.LoginResponseDTO;

public interface AuthenticationService {
    void registerUser(String username, String password) throws Exception;
    LoginResponseDTO loginUser(String username, String password) throws Exception;
}
