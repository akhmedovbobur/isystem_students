package com.example.isystem_students.repository;

import com.example.isystem_students.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Rooms, Integer> {

    Optional<Rooms> findByNameAndDeletedAtIsNull(String name);

    List<Rooms> findAllByDeletedAtIsNull();
}
