package com.qkrtprjs.happyexercise.config;

import com.qkrtprjs.happyexercise.config.auth.CustomOAuth2UserService;
import com.qkrtprjs.happyexercise.entitiy.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration  //빈에 등록(IoC관리)
@EnableWebSecurity  //시큐리티 필터가 등록이 된다(필터 설정을 여기서 해주겠다).
@EnableGlobalMethodSecurity(prePostEnabled = true)  //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다
@RequiredArgsConstructor
public class SecurityConfig {
//
//    @Bean   //IoC 가능 비밀번호를 암호화 하기위함
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder(); //이 값을 스프링이 관리
//    }
//    //시큐리티가 대신 로그인을 해줄때에 password를 가로채기를 하는데 해당 password가 뭘로 해쉬가 되어 회원가입이 되었었는지를 알아야
//    //같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교가 가능하다.
//
//
//
//
//    @Bean
//    RequestRejectedHandler requestRejectedHandler() {
//        return new HttpStatusRequestRejectedHandler();
//    }

    //새로 도입된 방식 사용하자
    private final CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   //csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
                .authorizeRequests()    //인증요청이들어올때
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/header", "/footer")    //auth/밑으로 들어오면 + 폴더 허용도 해줘야한다
                .permitAll()        //누구나 허용
                .antMatchers("/api/**").hasRole(Role.USER.name())//해당 주소는 USER의 권한을 가직 사람만 열람
                .anyRequest()   //그밖에는
                .authenticated()   //인증이 되어야된다
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()  //OAuth로그인 설정 시작
                .userInfoEndpoint() //로그인 성공이후 설정 시작
                .userService(customOAuth2UserService);

//                우리는 로그인을 OAuth로 진행
//                .formLogin()
//                .loginPage("/auth/loginForm")  //auth/ 밑이 아닌 주소들은 인증이필요하기때문에 /aut/loginForm으로 전달된다
//                .loginProcessingUrl("/auth/loginProc") //시프링 시큐리티가 해당 주소요청오는 로그인을 가로채고 대신 로그인해준다
//                .defaultSuccessUrl("/");    //정상적일때 이 주소로 보낸다.

        return http.build();
    }
//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

}