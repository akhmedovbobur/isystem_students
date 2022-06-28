package com.example.isystem_students.model;

import com.example.isystem_students.enums.Gender;
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
@Table(name = ("users"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("first_name"))
    private String firstName;

    @Column(name = ("second_name"))
    private String lastName;

    @Column(name = ("middle_name"))
    private String middleName;

    @Column(name = ("phone"),length = 45, unique = true)
    private String phone;

    @Column(name = ("gender"))
    private Gender gender;

    @Column(name = ("email"),unique = true)
    private String email;

    @Column(name = ("first address"))
    private String addressFirst;

    @Column(name = ("second address"))
    private String addressSecond;

    @Column(name = ("password"))
    private String password;

    @Column(name = ("status"))
    private Boolean status;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;

    @Column(name = ("updatedAt"))
    private LocalDateTime updatedAt;

    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;



    @ManyToOne
    @JoinColumn(name = ("user_types_id"), insertable = false, updatable = false)
    private UserTypes userTypes;

    @Column(name = ("user_types_id"))
    private Integer userTypesId;

    @ManyToOne
    @JoinColumn(name = ("user_image_id"), insertable = false, updatable = false)
    private UserImage userImage;

    @Column(name = ("user_image_id"))
    private Integer userImageId;






}
