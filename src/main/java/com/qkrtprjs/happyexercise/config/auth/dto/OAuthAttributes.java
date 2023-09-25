package com.qkrtprjs.happyexercise.config.auth.dto;

import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class OAuthAttributes { //소셜 로그인 API를 사용했을때에 소셜에서 가져온정보를 매핑하기위한 클래스
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String platform;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String platform) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.platform = platform;
    }




    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (registrationId.equals("naver")) {
            return ofNaver(userNameAttributeName, attributes);
        } else if (registrationId.equals("google")) {
            return ofGoogle(userNameAttributeName, attributes);
        } else
            return ofKakao(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .attributes(attributes)
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .picture((String) attributes.get("picture"))
                .nameAttributeKey(userNameAttributeName)
                .platform("GOOGLE")
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .attributes(attributes)
                .email((String) response.get("email"))
                .name((String) response.get("name"))
                .picture((String) response.get("profile_image"))
                .nameAttributeKey(userNameAttributeName)
                .platform("NAVER")
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");


        return OAuthAttributes.builder()
                .attributes(attributes)
                .email((String) kakao_account.get("email"))
                .name((String) profile.get("nickname"))
                .picture((String) profile.get("profile_image_url"))
                .nameAttributeKey(userNameAttributeName)
                .platform("KAKAO")
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .role(Role.USER)
                .platform(platform)
                .name(name)
                .picture(picture)
                .email(email)
                .build();
    }
}
