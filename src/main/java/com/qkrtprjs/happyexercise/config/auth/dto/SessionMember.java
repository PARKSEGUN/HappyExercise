package com.qkrtprjs.happyexercise.config.auth.dto;

import com.qkrtprjs.happyexercise.entitiy.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
public class SessionMember implements Serializable {    //세션에 저장하기위한 직렬화
    private Long id;
    private String email;
    private String name;
    private String picture;
    private String platform;


    @Builder
    public SessionMember(Long id, String email, String name, String picture, String platform) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.platform = platform;
    }

    public SessionMember(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.picture = member.getPicture();
        this.platform = member.getPlatform();
    }
}
