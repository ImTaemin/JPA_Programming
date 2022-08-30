package org.tmkim.jpashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tmkim.jpashop.domain.Member;
import org.tmkim.jpashop.repository.MemberRepository;

import java.util.List;

@Service
@Transactional
public class MemberService
{
    @Autowired
    MemberRepository memberRepository;

    //회원가입
    public Long join(Member member)
    {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member)
    {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (!findMembers.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    //전체 회원 조회
    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId)
    {
        return memberRepository.getReferenceById(memberId);
    }
}
