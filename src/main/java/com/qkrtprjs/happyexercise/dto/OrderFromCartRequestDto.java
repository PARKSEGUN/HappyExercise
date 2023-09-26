package com.qkrtprjs.happyexercise.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

public class OrderFromCartRequestDto {
    private List<CartItemResponseDto> cartItemResponseDtoList;

    private int orderPrice;

    @Builder
    public OrderFromCartRequestDto(List<CartItemResponseDto> cartItemResponseDtoList, int orderPrice) {
        this.cartItemResponseDtoList = cartItemResponseDtoList;
        this.orderPrice = orderPrice;
    }
}
