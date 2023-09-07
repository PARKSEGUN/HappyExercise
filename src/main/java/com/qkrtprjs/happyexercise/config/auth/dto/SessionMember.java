package com.qkrtprjs.happyexercise.config.auth.dto;

import com.qkrtprjs.happyexercise.member.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {    //세션에 저장하기위한 직렬화
    private String email;
    private String name;
    private String picture;

    public SessionMember(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.picture = member.getPicture();
    }
}
