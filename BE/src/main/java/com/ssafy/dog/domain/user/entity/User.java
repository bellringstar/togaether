package com.ssafy.dog.domain.user.entity;

import com.ssafy.dog.domain.user.model.Gender;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_login_id", length = 11, nullable = false)
    private String userLoginId;

    @Column(name = "user_pw", length = 256, nullable = false)
    private String userPw;

    @Column(name = "user_nickname", length = 30, nullable = false)
    private String userNickname;

    @Lob
    @Column(name = "user_picture")
    private String userPicture;

    @Column(name = "user_created_at", nullable = false)
    private LocalDateTime userCreatedAt;

    @Column(name = "user_updated_at", nullable = false)
    private LocalDateTime userUpdatedAt;

    @Column(name = "user_about_me", length = 200)
    private String userAboutMe;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gender", length = 1)
    private Gender userGender;

    @Column(name = "user_terms_agreed", nullable = false)
    private Boolean userTermsAgreed;

    // Getter, Setter, Constructors, and other methods

}
