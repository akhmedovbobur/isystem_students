package com.example.isystem_students.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = ("classes"))
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("name"))
    private String name;

    @Column(name = ("date"))
    private LocalDate date;

    @Column(name = ("status"))
    private Boolean status;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;

    @Column(name = ("updatedAt"))
    private LocalDateTime updatedAt;

    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;


    @ManyToOne
    @JoinColumn(name = ("rooms_id"), insertable = false, updatable = false)
    private Rooms rooms;

    @Column(name = ("rooms_id"))
    private Integer roomsId;

    @ManyToOne
    @JoinColumn(name = ("user_groups_id"), insertable = false, updatable = false)
    private UserGroups userGroups;

    @Column(name = ("user_groups_id"))
    private Integer userGroupsId;

    @ManyToOne
    @JoinColumn(name = ("attendance_type_id"), insertable = false, updatable = false)
    private AttendanceType attendanceType;

    @Column(name = ("attendance_type_id"))
    private Integer attendanceTypeId;
}
