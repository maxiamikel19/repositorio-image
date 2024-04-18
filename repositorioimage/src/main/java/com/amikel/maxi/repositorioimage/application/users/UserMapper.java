package com.amikel.maxi.repositorioimage.application.users;

import org.springframework.stereotype.Component;

import com.amikel.maxi.repositorioimage.domain.entity.User;

@Component
public class UserMapper {
    
    public User mapToUser(UserDTO uDto){

        return User.builder()
                .name(uDto.getName())
                .email(uDto.getEmail())
                .password(uDto.getPassword())
                .build();
    }
}
