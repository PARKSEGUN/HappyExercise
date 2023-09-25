package com.qkrtprjs.happyexercise.entitiy.order;

import com.qkrtprjs.happyexercise.dto.OrderItemResponseDto;
import com.qkrtprjs.happyexercise.entitiy.BaseTimeEntity;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class OrderItem extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int count;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @Builder
    public OrderItem(int count, int price, Order order, Item item) {
        this.count = count;
        this.price = price;
        this.order = order;
        this.item = item;
    }

    public static OrderItemResponseDto toDto(OrderItem orderItem) {
        return OrderItemResponseDto.builder()
                .id(orderItem.getId())
                .count(orderItem.getCount())
                .orderResponseDto(Order.toDto(orderItem.getOrder()))
                .itemResponseDto(Item.toDto(orderItem.getItem()))
                .price(orderItem.getPrice())
                .build();
    }

}
