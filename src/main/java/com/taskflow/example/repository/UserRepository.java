package com.taskflow.example.repository;

import com.taskflow.example.model.AppUser;
import com.taskflow.example.model.enums.Role;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    @Query("SELECT u FROM AppUser u WHERE u.email = ?1")
    Optional<AppUser> findByEmail(String email);
    AppUser findByRole(Role role);
}
