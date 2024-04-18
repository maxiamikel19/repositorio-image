package com.amikel.maxi.repositorioimage.application.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amikel.maxi.repositorioimage.domain.AccessToken;
import com.amikel.maxi.repositorioimage.domain.entity.User;
import com.amikel.maxi.repositorioimage.domain.exception.ObjectCreateDuplicationException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO){
        try {
            User user = userMapper.mapToUser(userDTO);
            user = userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (ObjectCreateDuplicationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authticate(@RequestBody CredentialUserDTO userDTO){
        AccessToken token = userService.authenticate(userDTO.getEmail(), userDTO.getPassword());
        if(token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password fail");
        }else{
            return ResponseEntity.ok().body(token);
        }
    }
}
