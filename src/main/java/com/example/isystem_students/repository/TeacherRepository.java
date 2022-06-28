package com.example.isystem_students.repository;

import com.example.isystem_students.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Optional<Teacher> findByIdAndDeletedAtIsNull(Integer id);


}
