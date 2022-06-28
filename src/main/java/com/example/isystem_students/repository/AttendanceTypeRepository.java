package com.example.isystem_students.repository;

import com.example.isystem_students.model.AttendanceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceTypeRepository extends JpaRepository<AttendanceType, Integer> {

    Optional<AttendanceType> findByNameAndDeletedAtIsNull(String name);

    List<AttendanceType> findAllByDeletedAtIsNull();
}
