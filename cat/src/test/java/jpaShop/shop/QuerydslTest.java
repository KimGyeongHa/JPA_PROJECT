package jpaShop.shop;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.QMember;
import jpaShop.shop.domain.member.service.response.FindMemberResponse;
import jpaShop.shop.domain.order.Order;
import jpaShop.shop.domain.order.QOrder;
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


    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void init(){
        jpaQueryFactory = new JPAQueryFactory(em);
    }


    @Test
    void Paging(){
        QueryResults<Tuple> tupleQueryResults =
                jpaQueryFactory
                .select(order, member)
                .from(member)
                .leftJoin(member.orders, order).fetchJoin()
                .offset(0)
                .limit(1)
                .fetchResults();
    }
    
    @Test
    void join(){
        List<Tuple> fetch = jpaQueryFactory
                .select(member, order)
                .from(member)
                .leftJoin(member.orders, order).fetchJoin()
                .fetch();
    }

    @Test
    void projection(){

        QOrder subOrder = new QOrder("subOrder");

        jpaQueryFactory
                .select(Projections.constructor(
                        FindMemberResponse.class
                        , member.address.city
                        , member.address.street
                        , member.address.zipcode
                        , ExpressionUtils.as(
                                JPAExpressions
                                        .select(subOrder.member.memberName)
                                        .from(subOrder)
                                        ,"memberName"
                        )
                )).from(member)
                .fetch();
    }


    @Test
    void booleanBuilder(){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(member.memberName.eq("김경삵"));



        jpaQueryFactory
                .selectFrom(member)
                .where(booleanBuilder)
                .fetch();


    }
}
