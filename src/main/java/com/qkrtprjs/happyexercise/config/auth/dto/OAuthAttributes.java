package com.qkrtprjs.happyexercise.config.auth.dto;

import com.qkrtprjs.happyexercise.member.Member;
import com.qkrtprjs.happyexercise.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.catalina.User;

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
        return new OAuthAttributes();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return new OAuthAttributes();
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
