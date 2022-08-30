package org.tmkim.jpashop.repository.custom;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;
import org.tmkim.jpashop.domain.Order;
import org.tmkim.jpashop.domain.OrderSearch;
import org.tmkim.jpashop.domain.QMember;
import org.tmkim.jpashop.domain.QOrder;

import java.util.List;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository
{
    public OrderRepositoryImpl()
    {
        super(Order.class);
    }

    @Override
    public List<Order> search(OrderSearch orderSearch)
    {
        QOrder order = QOrder.order;
        QMember member = QMember.member;

        JPQLQuery query = from(order);

        if (StringUtils.hasText(orderSearch.getMemberName()))
        {
            query.leftJoin(order.member, member)
                    .where(member.name.contains(orderSearch.getMemberName()));
        }

        if (orderSearch.getOrderStatus() != null)
        {
            query.where(order.status.eq(orderSearch.getOrderStatus()));
        }

        return query.fetch();
    }
}
