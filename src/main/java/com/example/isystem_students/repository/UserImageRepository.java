package com.example.isystem_students.repository;

import com.example.isystem_students.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Integer> {

    Optional<UserImage> findByIdAndDeletedAtIsNull(Integer id);
    Optional<UserImage> findByToken(String token);

}
