package com.example.isystem_students.repository;

import com.example.isystem_students.model.TeacherTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherTypesRepository extends JpaRepository<TeacherTypes, Integer> {

    Optional<TeacherTypes> findByNameAndDeletedAtIsNull(String name);

    List<TeacherTypes> findAllByDeletedAtIsNull();
}
