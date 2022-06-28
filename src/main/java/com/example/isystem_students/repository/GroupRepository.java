package com.example.isystem_students.repository;

import com.example.isystem_students.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findByName(String name);

    List<Group> findAllByDeletedAtIsNull();
}
