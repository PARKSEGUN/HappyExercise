package com.qkrtprjs.happyexercise.entitiy.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findByOrder(Order order);

     public void deleteByOrder(Order order);
}
