package com.qkrtprjs.happyexercise.entitiy.cart;

import com.qkrtprjs.happyexercise.entitiy.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
     Optional<Cart> findByMember(Member member);
}
