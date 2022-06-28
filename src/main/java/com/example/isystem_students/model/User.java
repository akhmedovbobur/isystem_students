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
@Table(name = ("users"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("first name"))
    private String firstname;

    @Column(name = ("second name"))
    private String lastname;

    @Column(name = ("middle name"))
    private String middleName;

    //private enum gender;

    @Column(name = ("phone"))
    private String phone;

    @Column(name = ("email"))
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
    @JoinColumn(name = ("user_types"), insertable = false, updatable = false)
    private UserTypes userTypes;

    @Column(name = ("user_types_id"))
    private Integer userTypesId;

    @ManyToOne
    @JoinColumn(name = ("user_image"), insertable = false, updatable = false)
    private UserImage userImage;

    @Column(name = ("user_image_id"))
    private Integer userImageId;






}
