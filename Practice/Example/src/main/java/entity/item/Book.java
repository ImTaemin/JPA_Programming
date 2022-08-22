package entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public abstract class Book extends Item
{
    private String author;
    private String isbn;
}