package org.tmkim.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.tmkim.jpashop.domain.Category;
import org.tmkim.jpashop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item
{
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //비즈니스 로직
    public void addStock(int quantity)
    {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) throws NotEnoughStockException
    {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0)
            throw new NotEnoughStockException("need more stock");

        this.stockQuantity = restStock;
    }
}
