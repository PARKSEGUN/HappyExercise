package com.qkrtprjs.happyexercise.controller;

import com.qkrtprjs.happyexercise.config.auth.LoginMember;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.ItemSaveRequestDto;
import com.qkrtprjs.happyexercise.dto.ItemUpdateRequestDto;
import com.qkrtprjs.happyexercise.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping("/api/item")
    private ResponseEntity<?> save(ItemSaveRequestDto itemSaveRequestDto, MultipartFile img, @LoginMember SessionMember member) throws Exception {

        Long id = itemService.save(itemSaveRequestDto, img, member);
        //RestController에서 리다이렉션 사용하는 방법
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/"));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @PutMapping("/api/item/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, ItemUpdateRequestDto itemUpdateRequestDto, MultipartFile img,
                                     @LoginMember SessionMember member) throws Exception {
        System.out.println(id);

        itemService.update(itemUpdateRequestDto, img, member);
        //RestController에서 리다이렉션 사용하는 방법
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/item/" + id));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @DeleteMapping("/api/item/{id}")
    private Long delete(@PathVariable Long id) {
        itemService.deleteById(id);
        return id;
    }
}
