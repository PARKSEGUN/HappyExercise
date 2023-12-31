package com.qkrtprjs.happyexercise.entitiy.member;

import com.qkrtprjs.happyexercise.dto.MemberResponseDto;
import com.qkrtprjs.happyexercise.entitiy.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String platform;

    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(String name, String email, String platform, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.platform = platform;
        this.picture = picture;
        this.role = role;
    }



    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
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
