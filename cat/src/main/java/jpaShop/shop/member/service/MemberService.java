package jpaShop.shop.member.service;

import jpaShop.shop.member.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    public Long saveMember(Member member);
    public Member findMember(Long id);
    public List<Member> findMembers();

}
