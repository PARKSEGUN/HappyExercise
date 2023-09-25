package com.qkrtprjs.happyexercise.service;

import com.qkrtprjs.happyexercise.config.auth.LoginMember;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.qkrtprjs.happyexercise.dto.ItemResponseDto;
import com.qkrtprjs.happyexercise.dto.ItemSaveRequestDto;
import com.qkrtprjs.happyexercise.dto.ItemUpdateRequestDto;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    private String  saveImg(MultipartFile img) throws Exception{
        //저장할 위치를 설정
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";

        UUID uuid = UUID.randomUUID();  //식별자 역할을 위함

        String imgName = uuid + "_" + img.getOriginalFilename();

        String imgPath = "/image/" + imgName;
        //파일을 넣어줄 껍데기 생성
        File file = new File(projectPath, imgName);

        //받아온 img 파일로 이동
        img.transferTo(file);
        return imgName;

    }

    private boolean deleteImg(String imgName){
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";
        File file = new File(projectPath, imgName);
        return file.delete();
    }


    @Transactional
    public Long save(ItemSaveRequestDto itemSaveRequestDto, MultipartFile img, SessionMember member) throws Exception {

        String imgName = saveImg(img);
        Member loginMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지않습니다"));

        Item item = itemSaveRequestDto.toEntity(itemSaveRequestDto, imgName, "/image/" + imgName, loginMember);
        return itemRepository.save(item).getId();
    }

    @Transactional(readOnly = true)
    public List<ItemResponseDto> findAll() {
        return itemRepository.findAll().stream()
                .map(item -> Item.toDto(item))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ItemResponseDto findById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("찾으시는 상품은 존재하지않습니다"));
        return Item.toDto(item);
    }

    @Transactional
    public Long update(ItemUpdateRequestDto itemUpdateRequestDto, MultipartFile img, SessionMember member) throws Exception{
        if (!img.getOriginalFilename().equals("")) {
            Member loginMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지않습니다"));
            Item item = itemRepository.findById(itemUpdateRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 ID 값은 존재하지않습니다!"));
            boolean deleteResult = deleteImg(item.getImgName());
            String imgName = saveImg(img);
            Item updateItem = itemUpdateRequestDto.toEntity(itemUpdateRequestDto, imgName, "/image/"+imgName, loginMember);
            return itemRepository.save(updateItem).getId();
        }else{
            Member loginMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일은 존재하지않습니다"));
            Item item = itemRepository.findById(itemUpdateRequestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 아이디값은 존재하지않습니다!"));
            Item updateItem=item.update(itemUpdateRequestDto);
            return itemRepository.save(updateItem).getId();
        }
    }

    @Transactional
    public void deleteById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID값이 존재하지않습니다!"));
        boolean deleteResult = deleteImg(item.getImgName());
        itemRepository.deleteById(id);
    }
}
