package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.UserEntity;

public interface RoleService {
    UserEntity changeUserRoleService(Long id) throws Exception;
    UserEntity changeAdminRoleService(Long id) throws Exception;

}
