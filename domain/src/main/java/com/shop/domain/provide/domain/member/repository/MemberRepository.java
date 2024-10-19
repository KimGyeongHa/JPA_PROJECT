package com.shop.domain.provide.domain.member.repository;

import com.shop.domain.provide.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findMemberByMemberName(String memberName);
}
