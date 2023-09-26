package com.qkrtprjs.happyexercise.controller;

import com.qkrtprjs.happyexercise.config.auth.LoginMember;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.OrderSaveRequestDto;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.item.ItemRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import com.qkrtprjs.happyexercise.service.CartItemService;
import com.qkrtprjs.happyexercise.service.CartService;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartApiController {
    private final CartService cartService;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartItemService cartItemService;

    @PostMapping("/api/cart")
    private Long save(@RequestBody OrderSaveRequestDto orderSaveRequestDto, @LoginMember SessionMember loginMember) {
        Member member = memberRepository.findByEmail(loginMember.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지 않습니다"));
        Item item = itemRepository.findById(orderSaveRequestDto.getItemResponseDto().getId()).orElseThrow(() -> new IllegalArgumentException("해당 ID는 존재하지 않습니다!"));
        int count = orderSaveRequestDto.getCount();

        return cartService.save(item, count, member);
    }

    @DeleteMapping("/api/cartItem/{id}")
    private Long delete(@PathVariable Long id) {
        cartItemService.delete(id);
        return id;
    }
}
