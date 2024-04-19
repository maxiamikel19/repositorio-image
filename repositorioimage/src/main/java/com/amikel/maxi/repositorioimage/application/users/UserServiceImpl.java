package com.amikel.maxi.repositorioimage.application.users;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amikel.maxi.repositorioimage.application.jwt.JwtService;
import com.amikel.maxi.repositorioimage.domain.AccessToken;
import com.amikel.maxi.repositorioimage.domain.entity.User;
import com.amikel.maxi.repositorioimage.domain.exception.ObjectCreateDuplicationException;
import com.amikel.maxi.repositorioimage.domain.service.UserService;
import com.amikel.maxi.repositorioimage.infra.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        var opUser = getByEmail(user.getEmail());
        if(opUser != null){
            throw new ObjectCreateDuplicationException("User email aleready teken");
        }
        encodeUserPassword(user);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public AccessToken authenticate(String email, String password) {
        var user = getByEmail(email);
        if(user == null){
            return null;
        }
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if(matches){
            return jwtService.generateToken(user);
        }
        return null;
    }

    private void encodeUserPassword(User user){
        String password = user.getPassword();
        String hashPassword = passwordEncoder.encode(password);
        user.setPassword(hashPassword);
    }
    
}
