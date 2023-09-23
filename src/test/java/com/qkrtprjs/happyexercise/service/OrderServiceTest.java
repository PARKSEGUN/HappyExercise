package com.qkrtprjs.happyexercise.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.ItemResponseDto;
import com.qkrtprjs.happyexercise.dto.MemberResponseDto;
import com.qkrtprjs.happyexercise.dto.OrderSaveRequestDto;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.item.ItemRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Role;
import com.qkrtprjs.happyexercise.entitiy.order.Order;
import com.qkrtprjs.happyexercise.entitiy.order.OrderItem;
import com.qkrtprjs.happyexercise.entitiy.order.OrderItemRepository;
import com.qkrtprjs.happyexercise.entitiy.order.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Test
    public void 주문등록() throws Exception {
        //given
        Member member = Member.builder()
                .name("name")
                .role(Role.USER)
                .picture("picture")
                .platform("kakao")
                .email("email")
                .build();
        Member saveMember = memberRepository.save(member);
        Item item = Item.builder()
                .detail("detail")
                .name("name")
                .price(100)
                .stock(100)
                .status("status")
                .imgName("imgName")
                .imgPath("imgPath")
                .member(saveMember)
                .build();
        Item saveItem = itemRepository.save(item);
        int count=5;


        int price = saveItem.getPrice() * 5;
        //when
        orderService.save(saveItem,saveMember, count);

        //then

        Order order = orderRepository.findAll().get(0);
        OrderItem orderItem = orderItemRepository.findAll().get(0);
        System.out.println(order.getStatus());
        System.out.println(price);
        assertThat(order.getPrice()).isEqualTo(price);
        assertThat(orderItem.getPrice()).isEqualTo(price);
    }
}
