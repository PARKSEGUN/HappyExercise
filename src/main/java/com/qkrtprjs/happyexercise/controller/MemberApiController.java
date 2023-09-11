package com.qkrtprjs.happyexercise.controller;

import com.qkrtprjs.happyexercise.dto.MemberSaveRequestDto;
import com.qkrtprjs.happyexercise.member.Member;
import com.qkrtprjs.happyexercise.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {


    private final MemberService memberService;

    @PostMapping("/api/member")
    private Long save(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.save(memberSaveRequestDto);
    }

    @DeleteMapping("/api/member/{id}")
    private Long delete(@PathVariable Long id) {
        memberService.delete(id);
        return id;
    }
}
