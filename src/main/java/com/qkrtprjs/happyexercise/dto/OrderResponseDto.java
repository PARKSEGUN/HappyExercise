package com.qkrtprjs.happyexercise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;

    private LocalDateTime date;

    private String status;

    private int price;

    private MemberResponseDto memberResponseDto;

    @Builder
    public OrderResponseDto(Long id, LocalDateTime date, String status, int price, MemberResponseDto memberResponseDto) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.price = price;
        this.memberResponseDto = memberResponseDto;
    }
}
