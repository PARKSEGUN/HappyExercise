package com.qkrtprjs.happyexercise.service;

import com.qkrtprjs.happyexercise.config.auth.LoginMember;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.ItemSaveRequestDto;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.item.ItemRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(ItemSaveRequestDto itemSaveRequestDto,MultipartFile img, SessionMember member) throws Exception {
        //저장할 위치를 설정
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";

        UUID uuid = UUID.randomUUID();  //식별자 역할을 위함

        String imgName = uuid + "_" + img.getOriginalFilename();
        String imgPath = "/image/" + imgName;
        //파일을 넣어줄 껍데기 생성
        File file = new File(projectPath, imgName);

        //받아온 img 파일로 이동
        img.transferTo(file);

        Member loginMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지않습니다"));

        Item item = itemSaveRequestDto.toEntity(itemSaveRequestDto, imgName, imgPath,loginMember);
        return itemRepository.save(item).getId();
    }
}
