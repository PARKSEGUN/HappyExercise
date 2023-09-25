package com.qkrtprjs.happyexercise.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDetailResponseDto {
    private Long id;

    private LocalDateTime date;

    private String status;

    private int price;

    private List<OrderItemResponseDto> orderItemResponseDtoList;

    @Builder
    public OrderDetailResponseDto(Long id, LocalDateTime date, String status, int price, List<OrderItemResponseDto> orderItemResponseDtoList) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.price = price;
        this.orderItemResponseDtoList = orderItemResponseDtoList;
    }
}
