package com.qkrtprjs.happyexercise.entitiy.cart;

import com.qkrtprjs.happyexercise.dto.CartResponseDto;
import com.qkrtprjs.happyexercise.entitiy.BaseTimeEntity;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Cart extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    public Cart(int price,Member member) {
        this.price = price;
        this.member = member;
    }


    public static CartResponseDto toDto(Cart cart) {

        return CartResponseDto.builder()
                .id(cart.getId())
                .price(cart.getPrice())
                .memberResponseDto(Member.toDto(cart.getMember()))
                .build();
    }

    public void saveItem(CartItem cartItem) {
        this.price += cartItem.getItem().getPrice()*cartItem.getCount();
    }

    public void deleteItem(CartItem cartItem) {
        this.price -= cartItem.getItem().getPrice()*cartItem.getCount();
    }
}
