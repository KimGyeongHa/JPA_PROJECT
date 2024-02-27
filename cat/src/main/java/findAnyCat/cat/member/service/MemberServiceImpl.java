package findAnyCat.cat.member.service;

import findAnyCat.cat.member.Member;
import findAnyCat.cat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long saveMember(Member member) {
        memberJoinValidation(member);
        return memberRepository.saveMember(member);
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findMember(id);
    }

    public void memberJoinValidation(Member member){
        List<Member> findMember = memberRepository.findByName(member.getName());
        if(!findMember.isEmpty()) throw new IllegalArgumentException("이미 등록 된 회원입니다.");
    }
}
