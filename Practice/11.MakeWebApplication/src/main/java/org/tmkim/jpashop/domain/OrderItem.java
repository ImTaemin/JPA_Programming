package org.tmkim.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.tmkim.jpashop.domain.item.Item;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem
{
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item; //주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order; //주문
    
    private int orderPrice; //주문가격
    private int count ; //주문 수량
}
