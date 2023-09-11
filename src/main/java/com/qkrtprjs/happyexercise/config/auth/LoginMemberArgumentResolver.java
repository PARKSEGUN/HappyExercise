package com.qkrtprjs.happyexercise.config.auth;

import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession session;

    //컨트롤러 메서드의 특정 파라미터를 지원하는지 판단, 즉 잘 사용했는지 판단

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginMemberAnnotation = parameter.getParameterAnnotation(LoginMember.class) != null;  //파라미터에 @LoginMember 어노테이션이 붙어있고
        boolean isMemberClass = SessionMember.class.equals(parameter.getParameterType()); //파라미터 클래스 타입이 SessionMember일 경우
        return isLoginMemberAnnotation && isMemberClass;    // true 리턴
    }


    //파라미터에 전달할 객체 생성
    //여기서는 HttpSession의 정보를 갖고와야한다
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return session.getAttribute("member");
    }
}
