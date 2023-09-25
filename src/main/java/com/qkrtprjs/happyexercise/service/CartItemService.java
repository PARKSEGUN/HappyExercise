package com.qkrtprjs.happyexercise.service;

import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.CartItemResponseDto;
import com.qkrtprjs.happyexercise.entitiy.cart.Cart;
import com.qkrtprjs.happyexercise.entitiy.cart.CartItem;
import com.qkrtprjs.happyexercise.entitiy.cart.CartItemRepository;
import com.qkrtprjs.happyexercise.entitiy.cart.CartRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartItemService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public List<CartItemResponseDto> findByMember(SessionMember sessionMember) {
        Member member = memberRepository.findByEmail(sessionMember.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지 않습니다!"));
        Cart cart = cartRepository.findByMember(member).orElseThrow(() -> new IllegalArgumentException("해당 member 값은 존재하지않습니다!"));
        return cartItemRepository.findByCart(cart).stream().map(cartItem -> CartItem.toDto(cartItem)).collect(Collectors.toList());

    }

    @Transactional
    public void delete(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지않습니다!"));
        //장바구니 가격 수정
        Cart cart = cartItem.getCart();
        cart.deleteItem(cartItem);
        cartItemRepository.delete(cartItem);
    }
}
