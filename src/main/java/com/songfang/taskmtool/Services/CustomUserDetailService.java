package com.songfang.taskmtool.Services;

import com.songfang.taskmtool.Domain.User;
import com.songfang.taskmtool.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if(user==null){
            new UsernameNotFoundException("User not found!");
        }
        return user;
    }

    @Transactional
    public User loadById(Long id){
        User user = userRepository.getById(id);
        if(user==null){
            new UsernameNotFoundException("User not found!");
        }
        return user;
    }
}
