package org.tmkim.jpashop.repository.custom;

import org.tmkim.jpashop.domain.Order;
import org.tmkim.jpashop.domain.OrderSearch;

import java.util.List;

public interface CustomOrderRepository
{
    public List<Order> search(OrderSearch orderSearch);
}
