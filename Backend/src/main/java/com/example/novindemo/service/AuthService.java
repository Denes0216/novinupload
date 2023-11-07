package com.example.novindemo.service;

import com.example.novindemo.dto.AuthResponseDto;
import com.example.novindemo.dto.LoginDto;
import com.example.novindemo.dto.RegisterDto;
import com.example.novindemo.entity.Role;
import com.example.novindemo.entity.UserEntity;
import com.example.novindemo.repository.RoleRepository;
import com.example.novindemo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository,
                       UserService userService, PasswordEncoder passwordEncoder,
                       JwtService jwtService, AuthenticationManager authenticationManager,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    public AuthResponseDto signup(RegisterDto request) {
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(request.getRole()).get());
        user.setRoles(roles);
        user.setUsername(request.getUsername());
        user.setLoginDate(new Date());

        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        return new AuthResponseDto(jwt, user.getRoles());
    }


    public AuthResponseDto signin(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        userRepository.setLoginDate(user.getId(), new Date());
        var jwt = jwtService.generateToken(user);
        var roles = userRepository.findRolesByUsername(user.getUsername());
        var id = user.getId();
        var name = user.getName();
        return new AuthResponseDto(jwt, roles, id, new Date(), name);
    }
}
