package com.example.novindemo.repository;

import com.example.novindemo.entity.Role;
import com.example.novindemo.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByUsername(String username);
    Boolean existsByUsername(String username);

    @Query("SELECT u.roles FROM UserEntity u WHERE u.username = :username")
    Set<Role> findRolesByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.loginDate = :loginDate WHERE u.id = :userId")
    void setLoginDate(@Param("userId") Long userId, @Param("loginDate") Date loginDate);
}
