package jpaShop.shop.domain.member.repository;

import com.sun.jdi.ClassType;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.projection.MemberProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findMemberByMemberName(String memberName);

    List<MemberProjections> findMemberProjectionByMemberName(@Param("memberName")String memberName);

    <T>List<T> findMemberClassProjectionByMemberName(@Param("memberName")String memberName, Class<T> T);

}
