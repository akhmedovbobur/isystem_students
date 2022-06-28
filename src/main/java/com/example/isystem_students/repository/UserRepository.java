package com.example.isystem_students.repository;

import com.example.isystem_students.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndDeletedAtIsNull(String username);

    Optional<User> findByEmailOrFirstName(String token , String name);
    Optional<User> findByEmailOrPhoneAndDeletedAtIsNull(String email, String contact);
    Optional<User> findByEmailAndPasswordAndDeletedAtIsNull(String email, String generateMD5);

    List<User> findByDeletedAtIsNull();
}

