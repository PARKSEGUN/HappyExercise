package com.qkrtprjs.happyexercise.dto;


import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ItemResponseDto {
    private Long id;

    private String detail;

    private String name;

    private int price;

    private int stock;   //재고

    private String status;

    private String imgName;

    private String imgPath;

    private MemberResponseDto memberResponseDto;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @Builder
    public ItemResponseDto(Long id, String detail, String name, int price, int stock, String status, String imgName,
                           String imgPath, MemberResponseDto memberResponseDto, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.detail = detail;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.memberResponseDto = memberResponseDto;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }


}
