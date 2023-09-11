package com.qkrtprjs.happyexercise.config.auth.dto;

import com.qkrtprjs.happyexercise.member.Member;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class SessionMember implements Serializable {    //세션에 저장하기위한 직렬화
    private Long id;
    private String email;
    private String name;
    private String picture;
    private String platform;


    public SessionMember(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.picture = member.getPicture();
        this.platform = member.getPlatform();
    }
}
