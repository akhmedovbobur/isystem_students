package com.example.isystem_students.repository;

import com.example.isystem_students.model.UserTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTypesRepository extends JpaRepository<UserTypes, Integer> {

    Optional<UserTypes> findByName(String name);

    List<UserTypes> findAllByDeletedAtIsNull();
}
