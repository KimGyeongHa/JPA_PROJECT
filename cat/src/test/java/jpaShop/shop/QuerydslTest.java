package jpaShop.shop;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.QMember;
import jpaShop.shop.domain.order.Order;
import org.hibernate.query.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

import static jpaShop.shop.domain.member.QMember.member;
import static jpaShop.shop.domain.order.QOrder.order;

@SpringBootTest
public class QuerydslTest {
    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init(){

    }


    @Test
    void QuerydslVerify(){
        JPAQueryFactory query = new JPAQueryFactory(em);

   /*     List<Member> memberList = query
                .selectFrom(member)
                .fetch();

        memberList.stream().forEach(item -> System.out.println(item.getMemberName()))*/;
        QueryResults<Tuple> tupleQueryResults =
                query
                .select(order, member)
                .from(member)
                .leftJoin(member.orders, order).fetchJoin()
                .offset(0)
                .limit(1)
                .fetchResults();

        System.out.println(tupleQueryResults);
    }


}
