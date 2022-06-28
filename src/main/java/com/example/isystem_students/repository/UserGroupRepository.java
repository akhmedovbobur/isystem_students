package com.example.isystem_students.repository;

import com.example.isystem_students.model.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public  interface UserGroupRepository extends JpaRepository<UserGroups, Integer> {
    Optional<UserGroups> findByIdAndDeletedAtIsNull(Integer id);

    List<UserGroups> findAllByDeletedAtIsNull();
}
