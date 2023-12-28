package com.myclothingstore.backend.service.impl;


import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.exception.UserNotFoundException;
import com.myclothingstore.backend.model.Role;
//import com.myclothingstore.backend.repository.CategoryRepository;
import com.myclothingstore.backend.repository.CategoryRepository;
import com.myclothingstore.backend.repository.ProductRepository;
import com.myclothingstore.backend.repository.RoleRepository;
import com.myclothingstore.backend.repository.UserRepository;
import com.myclothingstore.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public UserEntity changeUserRoleService(Long id){
        try {
            Optional<UserEntity> userEntityOptional = userRepository.findById(id);
            if (userEntityOptional.isPresent()){
                UserEntity userEntity = userEntityOptional.get();
                Set<Role> newRoles = new HashSet<>();
                Role role = new Role(1, "ADMIN");
                newRoles.add(role);
                userEntity.setAuthorities(newRoles);
                return userRepository.save(userEntity);
            } else {
                throw new UserNotFoundException("Пользователь с таким id не найден");
            }
        } catch (RuntimeException e){
            throw new RuntimeException("Ошибка смены роли");
        }
    }

    public UserEntity changeAdminRoleService(Long id){
        try {
            Optional<UserEntity> userEntityOptional = userRepository.findById(id);
            if (userEntityOptional.isPresent()){
                UserEntity userEntity = userEntityOptional.get();
                Set<Role> newRoles = new HashSet<>();
                Role role = new Role(2, "USER");
                newRoles.add(role);
                userEntity.setAuthorities(newRoles);
                return userRepository.save(userEntity);
            } else {
                throw new UserNotFoundException("Пользователь с таким id не найден");
            }
        } catch (Exception err){
            throw new RuntimeException("Ошибка смены роли");
        }
    }
}
