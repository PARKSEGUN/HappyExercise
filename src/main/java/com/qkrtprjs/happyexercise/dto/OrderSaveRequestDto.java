package com.qkrtprjs.happyexercise.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class OrderSaveRequestDto {
    private ItemResponseDto itemResponseDto;
    private int count;

    @Builder
    public OrderSaveRequestDto(ItemResponseDto itemResponseDto, int count) {
        this.itemResponseDto = itemResponseDto;
        this.count = count;
    }
}
