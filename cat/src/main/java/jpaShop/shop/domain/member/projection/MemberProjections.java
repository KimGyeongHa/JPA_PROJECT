package jpaShop.shop.domain.member.projection;

import org.springframework.beans.factory.annotation.Value;

public interface MemberProjections {
    @Value("#{target.memberName + ' ' + target.address.city}")
    String getMemberNameAndCity();

    @Value("#{target.address.city}")
    String getCity();
}
