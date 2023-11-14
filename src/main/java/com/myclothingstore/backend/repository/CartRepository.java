package com.myclothingstore.backend.repository;

import com.myclothingstore.backend.entity.CartEntity;
import com.myclothingstore.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByUserEntity(UserEntity userEntity);
}
