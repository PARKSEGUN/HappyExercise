package com.qkrtprjs.happyexercise.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  //이 어노테이션을 사용할 방식, 선언될 수 있는 장소 지정, 파라미터라면 파라미터로 사용가능
@Retention(RetentionPolicy.RUNTIME) //이 어노테이션의 라이프 사이클, 즉 언제까지 살아있을 것이냐
public @interface LoginMember {
}
