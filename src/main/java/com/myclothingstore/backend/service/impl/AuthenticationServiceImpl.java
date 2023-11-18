package com.myclothingstore.backend.service.impl;

import com.myclothingstore.backend.entity.CartEntity;
import com.myclothingstore.backend.model.DTO.LoginResponseDTO;
import com.myclothingstore.backend.entity.UserEntity;
import com.myclothingstore.backend.model.Role;
import com.myclothingstore.backend.repository.CartRepository;
import com.myclothingstore.backend.repository.RoleRepository;
import com.myclothingstore.backend.repository.UserRepository;
import com.myclothingstore.backend.service.AuthenticationService;
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
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServiceImpl tokenService;
    public void registerUser(String username, String password) throws Exception{
        if (userRepository.findByUsername(username).isEmpty()){
            String encodedPassword = passwordEncoder.encode(password);
            Role userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new Exception("Роль не найдена"));
            Set<Role> authorities = new HashSet<Role>();
            authorities.add(userRole);

            CartEntity cartEntity = new CartEntity();
            UserEntity userEntity = new UserEntity(username, encodedPassword, authorities);

            userRepository.save(userEntity);
            cartRepository.save(cartEntity);

            userEntity.setCartEntity(cartEntity);
            cartEntity.setUserEntity(userEntity);

            userRepository.save(userEntity);
            cartRepository.save(cartEntity);
        } else {
            throw new Exception("Такое имя уже существует");
        }
    }


    public LoginResponseDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        } catch (Exception err){
            return new LoginResponseDTO(null, "");
        }
    }
}
