package com.example.isystem_students.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = ("teachers"))
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("status"))
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = ("user_id"), insertable = false, updatable = false)
    private User user;

    @Column(name = ("user_id"))
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = ("teacher_types_id"), insertable = false, updatable = false)
    private TeacherTypes teacherTypes;

    @Column(name = ("teacher_types_id"))
    private Integer teacherTypesId;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;

    @Column(name = ("updatedAt"))
    private LocalDateTime updatedAt;

    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
