package com.qkrtprjs.happyexercise.service;

import com.qkrtprjs.happyexercise.dto.MemberSaveRequestDto;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequestDto memberSaveRequestDto) {
        return memberRepository.save(memberSaveRequestDto.toEntity(memberSaveRequestDto)).getId();
    }

    @Transactional
    public void delete(Long id) {
        //삭제할때에는 항상 그 값이 존재하는지 확인하고 삭제하는 과정을 거치자!
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다!"));
        memberRepository.delete(member);
    }
}
