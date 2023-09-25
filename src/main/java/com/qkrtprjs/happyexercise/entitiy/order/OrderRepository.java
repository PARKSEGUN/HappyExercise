package com.qkrtprjs.happyexercise.entitiy.order;

import com.qkrtprjs.happyexercise.entitiy.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByMember(Member member);
}
