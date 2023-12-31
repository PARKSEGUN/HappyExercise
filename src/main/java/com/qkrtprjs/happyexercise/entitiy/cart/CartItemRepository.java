package com.qkrtprjs.happyexercise.entitiy.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
     List<CartItem> findByCart(Cart cart);
}
