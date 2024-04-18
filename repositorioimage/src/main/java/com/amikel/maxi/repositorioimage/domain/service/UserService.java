package com.amikel.maxi.repositorioimage.domain.service;

import com.amikel.maxi.repositorioimage.domain.AccessToken;
import com.amikel.maxi.repositorioimage.domain.entity.User;

public interface UserService {
    
    User getByEmail(String email);
    User save(User user);
    AccessToken authenticate(String email, String password);
}
