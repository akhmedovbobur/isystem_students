package com.example.isystem_students.repository;

import com.example.isystem_students.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByNameAndDeletedAtIsNull(String name);

    List<Course> findAllByDeletedAtIsNull();
}
