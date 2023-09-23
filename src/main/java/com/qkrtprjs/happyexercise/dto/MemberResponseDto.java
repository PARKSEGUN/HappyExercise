package com.qkrtprjs.happyexercise.dto;

import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;

    private String name;

    private String email;

    private String platform;

    private String picture;

    private Role role;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @Builder
    public MemberResponseDto(Long id, String name, String email, String platform, String picture, Role role, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.platform = platform;
        this.picture = picture;
        this.role = role;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public static MemberResponseDto toDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .platform(member.getPlatform())
                .picture(member.getPicture())
                .role(member.getRole())
                .createdDate(member.getCreatedDate())
                .updatedDate(member.getUpdatedDate())
                .build();
    }
}
