package com.qkrtprjs.happyexercise.service;

import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.CartItemResponseDto;
import com.qkrtprjs.happyexercise.dto.CartResponseDto;
import com.qkrtprjs.happyexercise.entitiy.cart.Cart;
import com.qkrtprjs.happyexercise.entitiy.cart.CartItem;
import com.qkrtprjs.happyexercise.entitiy.cart.CartItemRepository;
import com.qkrtprjs.happyexercise.entitiy.cart.CartRepository;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Long save(Item item, int count, Member member) {
    //장바구니가 존재하는지 확인
        if (!cartRepository.findByMember(member).isPresent()) {
            cartRepository.save(
                    Cart.builder()
                            .price(0)
                            .member(member)
                            .build()
            );
        }

        Cart cart = cartRepository.findByMember(member).orElseThrow(() -> new IllegalArgumentException("해당 member 값은 존재하지않습니다!"));
        CartItem cartItem = cartItemRepository.save(CartItem.builder()
                .count(count)
                .item(item)
                .cart(cart)
                .build());
        cart.saveItem(cartItem);
        return cartItem.getId();
    }


    public CartResponseDto findByMember(SessionMember sessionMember) {
        Member member = memberRepository.findByEmail(sessionMember.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지않습니다!"));
        Cart cart = cartRepository.findByMember(member).orElseThrow(() -> new IllegalArgumentException("해당 member는 존재하지않습니다!"));
        return Cart.toDto(cart);
    }
}
