package com.example.demo.dto;

import com.example.demo.constant.Role;
import com.example.demo.entity.Member;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message= "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;

    @Builder
    public MemberFormDto(String name, String email, String password, String address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public Member toAdminEntity(PasswordEncoder passwordEncoder){
        return Member.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .address(address)
                .role(Role.ADMIN)
                .build();
    }

    public Member toUserEntity(PasswordEncoder passwordEncoder){
        return Member.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .address(address)
                .role(Role.USER)
                .build();
    }
}
