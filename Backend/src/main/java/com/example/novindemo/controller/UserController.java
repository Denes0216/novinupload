package com.example.novindemo.controller;

import com.example.novindemo.entity.Role;
import com.example.novindemo.entity.UserEntity;
import com.example.novindemo.repository.RoleRepository;
import com.example.novindemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
        UserEntity user = userService.getUserById(userId);

        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();

        return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{userId}/roles")
    public ResponseEntity<UserEntity> updateUserRoles(@PathVariable Long userId, @RequestBody Set<String> newRoles) {
        UserEntity user = userService.getUserById(userId);

        if (user != null) {
            Set<Role> roles = newRoles.stream()
                    .map(roleName -> roleRepository.findByName(roleName).orElse(null))
                    .filter(role -> role != null)
                    .collect(Collectors.toSet());

            user.setRoles(roles);
            userService.updateUser(user);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        UserEntity user = userService.getUserById(userId);

        if (user != null) {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
