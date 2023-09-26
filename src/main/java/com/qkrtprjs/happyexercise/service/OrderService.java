package com.qkrtprjs.happyexercise.service;

import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.*;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.item.ItemRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import com.qkrtprjs.happyexercise.entitiy.order.Order;
import com.qkrtprjs.happyexercise.entitiy.order.OrderItem;
import com.qkrtprjs.happyexercise.entitiy.order.OrderItemRepository;
import com.qkrtprjs.happyexercise.entitiy.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    @Transactional
    public Long save(Item item, Member member, int count) {

        Order order = Order.builder()
                .date(LocalDateTime.now())
                .status("주문완료")
                .price(item.getPrice() * count)
                .member(member)
                .build();
        OrderItem orderItem = OrderItem.builder()
                .count(count)
                .price(count * item.getPrice())
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);
        return orderRepository.save(order).getId();
    }

    public List<OrderDetailResponseDto> orderList(Member member) {
        List<OrderDetailResponseDto> dtoList = new ArrayList<>();

        List<Order> orderList = orderRepository.findByMember(member);
        for (Order order : orderList) {
            List<OrderItemResponseDto> orderItemResponseDtoList =
                    orderItemRepository.findByOrder(order).stream().map(orderItem -> OrderItem.toDto(orderItem)).collect(Collectors.toList());
            dtoList.add(
                    OrderDetailResponseDto.builder()
                            .id(order.getId())
                            .date(order.getDate())
                            .status(order.getStatus())
                            .price(order.getPrice())
                            .orderItemResponseDtoList(orderItemResponseDtoList)
                            .build()
            );
        }
        return dtoList;
    }

    public OrderDetailResponseDto orderDetail(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id값은 존재하지 않습니다!"));
        List<OrderItemResponseDto> orderItemResponseDtoList =
                orderItemRepository.findByOrder(order).stream().map(orderItem -> OrderItem.toDto(orderItem)).collect(Collectors.toList());
        return OrderDetailResponseDto.builder()
                .id(order.getId())
                .date(order.getDate())
                .status(order.getStatus())
                .price(order.getPrice())
                .orderItemResponseDtoList(orderItemResponseDtoList)
                .build();
    }

    @Transactional
    public void delete(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id는 존재하지않습니다"));
        orderItemRepository.deleteByOrder(order);
        orderRepository.delete(order);
    }

    @Transactional
    public Long saveFromCart(List<CartItemResponseDto> cartItemResponseDtoList,int orderPrice, SessionMember sessionMember) {
        Member member = memberRepository.findByEmail(sessionMember.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지않습니다!"));
        Order order = orderRepository.save(Order.builder()
                .member(member)
                .price(orderPrice)
                .status("주문완료")
                .date(LocalDateTime.now())
                .build());
        for (CartItemResponseDto cartItemResponseDto : cartItemResponseDtoList) {
            Item item = itemRepository.findById(cartItemResponseDto.getItemResponseDto().getId()).orElseThrow(() -> new IllegalArgumentException("해당 아이디는 존재하지않습니다!"));
            orderItemRepository.save(OrderItem.builder()
                    .item(item)
                    .order(order)
                    .count(cartItemResponseDto.getCount())
                    .price(item.getPrice() * cartItemResponseDto.getCount())
                    .build());
        }
        return order.getId();
    }


}
