package com.qkrtprjs.happyexercise.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.qkrtprjs.happyexercise.dto.*;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Role;
import com.qkrtprjs.happyexercise.entitiy.order.Order;
import com.qkrtprjs.happyexercise.entitiy.order.OrderItem;
import com.qkrtprjs.happyexercise.entitiy.order.OrderItemRepository;
import com.qkrtprjs.happyexercise.entitiy.order.OrderRepository;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles = "USER")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;


    @After
    public void tearDown() throws Exception {
        orderItemRepository.deleteAll();
        orderRepository.deleteAll();
    }

//    @Test
//    public void 주문등록() throws Exception {
//        //given
//        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
//                .id(11L)
//                .name("name")
//                .email("email")
//                .platform("platform")
//                .picture("picture")
//                .role(Role.USER)
//                .createdDate(LocalDateTime.now())
//                .updatedDate(LocalDateTime.now())
//                .build();
//
//
//        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
//                .id(11L)
//                .detail("detail")
//                .name("name")
//                .price(100)
//                .stock(100)
//                .status("status")
//                .imgName("imgName")
//                .imgPath("imgPath")
//                .memberResponseDto(memberResponseDto)
//                .createdDate(LocalDateTime.now())
//                .updatedDate(LocalDateTime.now())
//                .build();
//
//        OrderSaveRequestDto orderSaveRequestDto = OrderSaveRequestDto.builder()
//                .count(10)
//                .itemResponseDto(itemResponseDto)
//                .build();
//        String url = "http://localhost:" + port + "/api/order";
//        //when
//        mvc.perform(post(url)
//                        .contentType(MediaType.APPLICATION_JSON)    //보낼 형식
//                        .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(orderSaveRequestDto)))   //보낼 데이터, ObjectMapper로 문자열 JSON 형태로 변환
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        //then
//        int price= orderSaveRequestDto.getCount()*orderSaveRequestDto.getItemResponseDto().getPrice();
//        Order order = orderRepository.findAll().get(0);
//        OrderItem orderItem = orderItemRepository.findAll().get(0);
//        assertThat(order.getPrice()).isEqualTo(price);
//        assertThat(orderItem.getPrice()).isEqualTo(price);
//    }

}
