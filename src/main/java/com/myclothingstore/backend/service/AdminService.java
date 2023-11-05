package com.myclothingstore.backend.service;


import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.model.DTO.ChangeUserDTO;
import com.myclothingstore.backend.model.Role;
import com.myclothingstore.backend.repository.RoleRepository;
import com.myclothingstore.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserEntity changeUserRoleService(Long id) throws Exception{
        try {
            Optional<UserEntity> userEntityOptional = userRepository.findById(id);
            if (userEntityOptional.isPresent()){
                UserEntity userEntity = userEntityOptional.get();
                Set<Role> newRoles = new HashSet<>();
                Role role = new Role(1, "ADMIN");
                newRoles.add(role);
                userEntity.setAuthorities(newRoles);
                return userRepository.save(userEntity);
            }
            return null;
        } catch (Exception err){
            throw new Exception("Ошибка смены роли");
        }
    }

    public UserEntity changeAdminRoleService(Long id) throws Exception{
        try {
            Optional<UserEntity> userEntityOptional = userRepository.findById(id);
            if (userEntityOptional.isPresent()){
                UserEntity userEntity = userEntityOptional.get();
                Set<Role> newRoles = new HashSet<>();
                Role role = new Role(2, "USER");
                newRoles.add(role);
                userEntity.setAuthorities(newRoles);
                return userRepository.save(userEntity);
            }
            return null;
        } catch (Exception err){
            throw new Exception("Ошибка смены роли");
        }
    }

    public CategoryEntity addCategory(CategoryEntity categoryEntity) throws Exception{
        try{

        } catch (Exception err) {

        }
    }
}
