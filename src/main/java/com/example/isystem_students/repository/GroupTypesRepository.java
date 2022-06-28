package com.example.isystem_students.repository;

import com.example.isystem_students.model.GroupTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupTypesRepository  extends JpaRepository<GroupTypes, Integer> {

    Optional<GroupTypes> findByNameAndDeletedAtIsNull(String name);

    List<GroupTypes> findAllByDeletedAtIsNull();
}
