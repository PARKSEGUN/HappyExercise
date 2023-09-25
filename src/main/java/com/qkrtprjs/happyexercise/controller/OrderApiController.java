package com.qkrtprjs.happyexercise.controller;


import com.qkrtprjs.happyexercise.config.auth.LoginMember;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.OrderSaveRequestDto;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.item.ItemRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import com.qkrtprjs.happyexercise.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderService orderService;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/api/order")
    private Long save(@RequestBody OrderSaveRequestDto orderSaveRequestDto, @LoginMember SessionMember loginMember) {
        Item item = itemRepository.findById(orderSaveRequestDto.getItemResponseDto().getId()).orElseThrow(() -> new IllegalArgumentException("해당 ID는 존재하지 않습니다!"));
        int count = orderSaveRequestDto.getCount();
        Member member = memberRepository.findByEmail(loginMember.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지 않습니다"));

        return orderService.save(item, member, count);
    }

    @DeleteMapping("/api/order/{id}")
    private Long delete(@PathVariable Long id) {
        orderService.delete(id);
        return id;
    }
}
