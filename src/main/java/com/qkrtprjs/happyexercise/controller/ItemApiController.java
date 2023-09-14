package com.qkrtprjs.happyexercise.controller;

import com.qkrtprjs.happyexercise.config.auth.LoginMember;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.ItemSaveRequestDto;
import com.qkrtprjs.happyexercise.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping("/api/item")
    private Long save(ItemSaveRequestDto itemSaveRequestDto, MultipartFile img,@LoginMember SessionMember member) throws Exception{
        System.out.println(itemSaveRequestDto);
        System.out.println(img);
        return itemService.save(itemSaveRequestDto,img,member);
    }
}
