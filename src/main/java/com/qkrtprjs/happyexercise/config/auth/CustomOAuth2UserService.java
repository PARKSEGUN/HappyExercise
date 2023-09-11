package com.qkrtprjs.happyexercise.config.auth;

import com.qkrtprjs.happyexercise.config.auth.dto.OAuthAttributes;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.member.Member;
import com.qkrtprjs.happyexercise.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;    //db에 저장하기위함
    private final HttpSession httpSession;  //세션에 저장하기위함

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();    //OAuth2UserService 생성
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);    //OAuth2User 객체 생성 loadUser 를 통한

        String registrationId = userRequest.getClientRegistration().getRegistrationId();  //구글, 네이버 카카오 알기위한 id
        //무슨값인지 알아보기
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //무슨값인지 알아보기
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes()); //OAuthAttributes 생성


        Member member = saveOrUpdate(attributes);
        httpSession.setAttribute("member", new SessionMember(member));


        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRole().getKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findByEmail(attributes.getEmail())
                //람다함수 entity는 현재 진행되는 entity를 말함 즉, Member
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture())) //기존의 회원이라고 찾아진다면 Member의 update 메서드를 실행
                .orElse(attributes.toEntity());
        return memberRepository.save(member);
    }
}
