package com.example.isystem_students.repository;

import com.example.isystem_students.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassesRepository  extends JpaRepository<Classes, Integer> {

    Optional<Classes> findByName(String name);

    List<Classes> findAllByDeletedAtIsNull();
}
