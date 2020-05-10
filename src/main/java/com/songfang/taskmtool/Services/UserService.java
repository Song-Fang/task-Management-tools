package com.songfang.taskmtool.Services;

import com.songfang.taskmtool.Domain.User;
import com.songfang.taskmtool.Exception.UsernameAlreadyExsitException;
import com.songfang.taskmtool.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser){

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        }catch(Exception e){
            throw new UsernameAlreadyExsitException("Username: "+newUser.getUsername()+" already exists!");
        }
    }
}
