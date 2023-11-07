package com.example.novindemo.controller;

import com.example.novindemo.dto.AuthResponseDto;
import com.example.novindemo.dto.LoginDto;
import com.example.novindemo.dto.RegisterDto;
import com.example.novindemo.repository.UserRepository;
import com.example.novindemo.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
        }

        AuthResponseDto responseDto = authService.signup(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse) {
        if (!userRepository.existsByUsername(loginDto.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        AuthResponseDto response = authService.signin(loginDto);
        httpServletResponse.setHeader("Authorization", response.getToken());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
