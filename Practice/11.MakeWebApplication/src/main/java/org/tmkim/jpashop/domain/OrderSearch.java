package org.tmkim.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;
import static org.tmkim.jpashop.domain.OrderSpec.memberNameLike;
import static org.tmkim.jpashop.domain.OrderSpec.orderStatusEq;

@Getter @Setter
public class OrderSearch
{
    private String memberName; //회원 이름
    private OrderStatus orderStatus; //주문 상태(ORDER, CANCEL)

    public Specification<Order> toSpecification()
    {
        return where(memberNameLike(memberName)).and(orderStatusEq(orderStatus));
    }
}
