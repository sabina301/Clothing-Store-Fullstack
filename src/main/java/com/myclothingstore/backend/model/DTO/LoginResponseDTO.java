package com.myclothingstore.backend.model.DTO;

import com.myclothingstore.backend.entity.UserEntity;

public class LoginResponseDTO {
    private UserEntity user;
    private String jwt;

    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(UserEntity user, String jwt){
        this.user = user;
        this.jwt = jwt;
    }

    public UserEntity getUser(){
        return this.user;
    }

    public void setUser(UserEntity user){
        this.user = user;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

}