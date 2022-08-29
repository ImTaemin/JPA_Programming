package org.tmkim.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.tmkim.jpashop.domain.Order;
import org.tmkim.jpashop.domain.item.Item;

import javax.persistence.*;

@Getter @Setter
@Entity
@DiscriminatorValue("D")
public class Delivery extends Item
{
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]
}
