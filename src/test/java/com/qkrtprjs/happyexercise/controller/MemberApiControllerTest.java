package com.qkrtprjs.happyexercise.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qkrtprjs.happyexercise.dto.MemberSaveRequestDto;
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles = "USER")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MemberApiControllerTest {

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
    public void member_등록() throws Exception {
        //given
        String name = "qkrtprjs";
        String email = "qkrtprjs456";
        String platform = "naver";
        String picture = "picture";
        MemberSaveRequestDto dto = MemberSaveRequestDto.builder()
                .name(name)
                .email(email)
                .platform(platform)
                .picture(picture)
                .build();
        String url = "http://localhost:" + port + "/api/member";
        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)    //보낼 형식
                        .content(new ObjectMapper().writeValueAsString(dto)))   //보낼 데이터, ObjectMapper로 문자열 JSON 형태로 변환
                .andExpect(status().isOk())
                .andDo(print());

        //then
        Member member = memberRepository.findAll().get(0);

        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getPlatform()).isEqualTo(platform);
        assertThat(member.getPicture()).isEqualTo(picture);
        assertThat(member.getEmail()).isEqualTo(email);
    }

    @Test
    public void member_삭제() throws Exception {
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
        memberRepository.save(member);
        Long id = 1L;
        String url = "http://localhost:" + port + "/api/member/"+id;
        System.out.println(url);
        //when
        mvc.perform(delete(url))
                .andExpect(status().isOk())
                .andDo(print());
        //then
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }
}
