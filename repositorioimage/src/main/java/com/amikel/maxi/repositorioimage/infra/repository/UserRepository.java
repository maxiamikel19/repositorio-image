package com.amikel.maxi.repositorioimage.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amikel.maxi.repositorioimage.domain.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

    User findByEmail(String email);
    
}
