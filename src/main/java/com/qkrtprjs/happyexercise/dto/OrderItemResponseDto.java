package com.qkrtprjs.happyexercise.dto;

import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
public class OrderItemResponseDto {

    private Long id;

    private int count;

    private int price;

    private OrderResponseDto orderResponseDto;

    private ItemResponseDto itemResponseDto;

    @Builder
    public OrderItemResponseDto(Long id, int count, int price, OrderResponseDto orderResponseDto, ItemResponseDto itemResponseDto) {
        this.id = id;
        this.count = count;
        this.price = price;
        this.orderResponseDto = orderResponseDto;
        this.itemResponseDto = itemResponseDto;
    }
}
