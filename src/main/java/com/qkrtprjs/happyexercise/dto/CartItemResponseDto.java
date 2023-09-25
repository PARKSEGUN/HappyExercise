package com.qkrtprjs.happyexercise.dto;

import com.qkrtprjs.happyexercise.entitiy.BaseTimeEntity;
import com.qkrtprjs.happyexercise.entitiy.cart.Cart;
import com.qkrtprjs.happyexercise.entitiy.cart.CartItem;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
public class CartItemResponseDto {

    private Long id;

    private int count;

    private CartResponseDto cartResponseDto;

    private ItemResponseDto itemResponseDto;

    @Builder

    public CartItemResponseDto(Long id, int count, CartResponseDto cartResponseDto, ItemResponseDto itemResponseDto) {
        this.id = id;
        this.count = count;
        this.cartResponseDto = cartResponseDto;
        this.itemResponseDto = itemResponseDto;
    }
}