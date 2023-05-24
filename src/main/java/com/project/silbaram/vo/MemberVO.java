package com.project.silbaram.vo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private Long mid;
    private String userId;
    private String password;
    private String nickName;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String phoneNumber;
    private String email;
    private String zipcode;
    private String address1;
    private String address2;
    private String socialLogin;
    private boolean isAdmin;
    private LocalDate regDate;
}