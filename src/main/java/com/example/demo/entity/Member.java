package com.example.demo.entity;


import com.example.demo.constant.Role;
import com.example.demo.dto.MemberFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@NoArgsConstructor
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String name, String email, String address, Role role, String password){
        this.name = name;
        this.email = email;
        this.address = address;
        this.role = role;
        this.password = password;
    }

    public static Member createUserMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        return memberFormDto.toUserEntity(passwordEncoder);
    }

    public static Member createAdminMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        return memberFormDto.toAdminEntity(passwordEncoder);
    }
}
