package com.qkrtprjs.happyexercise.service;

import com.qkrtprjs.happyexercise.entitiy.cart.Cart;
import com.qkrtprjs.happyexercise.entitiy.cart.CartItem;
import com.qkrtprjs.happyexercise.entitiy.cart.CartItemRepository;
import com.qkrtprjs.happyexercise.entitiy.cart.CartRepository;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public Long save(Item item, int count, Member member) {
//장바구니가 존재하는지 확인
        if (!cartRepository.findByMember(member).isPresent()) {
            cartRepository.save(
                    Cart.builder()
                            .member(member)
                            .build()
            );
        }
        Cart cart = cartRepository.findByMember(member).orElseThrow(() -> new IllegalArgumentException("해당 member 값은 존재하지않습니다!"));
        return cartItemRepository.save(CartItem.builder()
                .count(count)
                .item(item)
                .cart(cart)
                .build()).getId();
    }
}
