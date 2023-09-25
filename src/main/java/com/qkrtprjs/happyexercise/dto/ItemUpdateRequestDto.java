package com.qkrtprjs.happyexercise.dto;


import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter //setter를 넣어주어야 폼으로 전달했을때에 받아오는것이가능 그렇다면 ajax로 넘겨주었을때도 필요한가
@NoArgsConstructor
@ToString
public class ItemUpdateRequestDto {

    private Long id;

    private String name;

    private String detail;

    private int price;

    private int stock;   //재고

//    private String status;    Entity에서 Enum으로 처리하자
//    private Long memberId;    LoginUser에서 받아오기
    //   private MultipartFile img;        @ReqeustPart 로 따로 받을것


    @Builder
    public ItemUpdateRequestDto(Long id,String name, String detail, int price, int stock) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.stock = stock;
    }


    public Item toEntity(ItemUpdateRequestDto itemUpdateRequestDto, String imgName, String imgPath,Member member) {
        return Item.builder()
                .id(itemUpdateRequestDto.getId())
                .detail(itemUpdateRequestDto.getDetail())
                .name(itemUpdateRequestDto.getName())
                .price(itemUpdateRequestDto.getPrice())
                .stock(itemUpdateRequestDto.getStock())
                .status("판매가능")
                .imgName(imgName)
                .imgPath(imgPath)
                .member(member)
                .build();

    }
}
