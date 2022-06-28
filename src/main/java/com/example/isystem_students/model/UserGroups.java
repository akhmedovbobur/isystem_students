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
@Table(name = ("user_groups"))
public class UserGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("status"))
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = ("group"), insertable = false, updatable = false)
    private Group group;

    @Column(name = ("group_id"))
    private Integer groupId;

    @ManyToOne
    @JoinColumn(name = ("user"), insertable = false, updatable = false)
    private User user;

    @Column(name = ("user_id"))
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = ("teacher"), insertable = false, updatable = false)
    private Teacher teacher;

    @Column(name = ("teacher_id"))
    private Integer teacherId;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;

    @Column(name = ("updatedAt"))
    private LocalDateTime updatedAt;

    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;

}
