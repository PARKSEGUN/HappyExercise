package com.qkrtprjs.happyexercise.dto;

import com.qkrtprjs.happyexercise.entitiy.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
public class CartResponseDto {

    private Long id;

    private int price;

    private MemberResponseDto memberResponseDto;

    @Builder

    public CartResponseDto(Long id, int price, MemberResponseDto memberResponseDto) {
        this.id = id;
        this.price = price;
        this.memberResponseDto = memberResponseDto;
    }
}
