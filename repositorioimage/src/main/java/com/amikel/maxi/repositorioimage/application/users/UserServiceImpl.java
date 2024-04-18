package com.amikel.maxi.repositorioimage.application.users;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return userRepository.save(user);
    }

    @Override
    public AccessToken authenticate(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }
    
}
