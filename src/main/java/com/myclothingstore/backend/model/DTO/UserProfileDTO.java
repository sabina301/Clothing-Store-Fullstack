package com.myclothingstore.backend.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfileDTO {
    private String username;
    public UserProfileDTO(String username){
        this.username = username;
    }
}

