package com.qkrtprjs.happyexercise.dto;

import com.qkrtprjs.happyexercise.member.Member;
import com.qkrtprjs.happyexercise.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String name;

    private String email;

    private String platform;

    private String picture;


    @Builder

    public MemberSaveRequestDto(String name, String email, String platform, String picture) {
        this.name = name;
        this.email = email;
        this.platform = platform;
        this.picture = picture;
    }

    public Member toEntity(MemberSaveRequestDto memberSaveRequestDto) {
        return Member.builder()
                .name(memberSaveRequestDto.getName())
                .email(memberSaveRequestDto.getEmail())
                .platform(memberSaveRequestDto.getPlatform())
                .picture(memberSaveRequestDto.getPicture())
                .role(Role.USER)
                .build();
    }

}
