package com.qkrtprjs.happyexercise.entitiy.item;

import com.qkrtprjs.happyexercise.entitiy.BaseTimeEntity;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Item extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String detail;

    private String name;

    private int price;

    private int stock;   //재고

    private String status;

    //이미지를 갖고오기위한 필드
    private String imgName;
    private String imgPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    public Item(String detail, String name, int price, int stock, String status, String imgName, String imgPath, Member member) {
        this.detail = detail;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.member = member;
    }
}
