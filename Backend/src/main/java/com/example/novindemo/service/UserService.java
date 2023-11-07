package com.example.novindemo.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.novindemo.entity.UserEntity;
import com.example.novindemo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findUserByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public UserEntity save(UserEntity newUser) {
        if (newUser.getId() == null) {
            newUser.setLoginDate(new Date());
        }
        return userRepository.save(newUser);
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
