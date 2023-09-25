package com.qkrtprjs.happyexercise.entity;


import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Role;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WithMockUser(roles = "USER")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseTimeEntityTest {
    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MemberRepository memberRepository;


    @After
    public void tearDown() throws Exception {
        memberRepository.deleteAll();
    }


    @Test
    public void Audititng테스트() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusSeconds(1);  //확실하게 이후임을 확인하기위해서 1초를 댕겨준다


        //given
        String name = "qkrtprjs";
        String email = "qkrtprjs456";
        String platform = "naver";
        String picture = "picture";

        Member member = Member.builder()
                .role(Role.USER)
                .platform(platform)
                .name(name)
                .email(email)
                .picture(picture)
                .build();
        //when
        memberRepository.save(member);
        List<Member> all = memberRepository.findAll();
        Member m = all.get(0);
        //then
        System.out.println(now);
        System.out.println(m.getCreatedDate());
        assertThat(m.getCreatedDate()).isAfter(now);
        assertThat(m.getUpdatedDate()).isAfter(now);

    }

}
