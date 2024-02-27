package findAnyCat.cat.member.service;

import findAnyCat.cat.member.Member;
import findAnyCat.cat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    public Long saveMember(Member member);
    public Member findMember(Long id);
    public List<Member> findMembers();

}
