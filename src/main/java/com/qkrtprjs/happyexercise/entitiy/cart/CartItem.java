package com.qkrtprjs.happyexercise.entitiy.cart;

import com.qkrtprjs.happyexercise.dto.CartItemResponseDto;
import com.qkrtprjs.happyexercise.entitiy.BaseTimeEntity;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CartItem extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @Builder
    public CartItem(int count, Cart cart, Item item) {
        this.count = count;
        this.cart = cart;
        this.item = item;
    }

    public static CartItemResponseDto toDto(CartItem cartItem) {
        return CartItemResponseDto.builder()
                .id(cartItem.getId())
                .cartResponseDto(Cart.toDto(cartItem.getCart()))
                .count(cartItem.getCount())
                .itemResponseDto(Item.toDto(cartItem.getItem()))
                .build();
    }
}
