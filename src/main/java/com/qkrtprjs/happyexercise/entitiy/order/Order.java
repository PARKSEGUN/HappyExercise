package com.qkrtprjs.happyexercise.entitiy.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qkrtprjs.happyexercise.dto.OrderResponseDto;
import com.qkrtprjs.happyexercise.entitiy.BaseTimeEntity;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")

public class Order extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private String status;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;


    @Builder
    public Order(LocalDateTime date, String status, int price, Member member) {
        this.date = date;
        this.status = status;
        this.price = price;
        this.member = member;
    }

    public static OrderResponseDto toDto(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .date(order.getDate())
                .status(order.getStatus())
                .price(order.getPrice())
                .memberResponseDto(Member.toDto(order.getMember()))
                .build();
    }

}
