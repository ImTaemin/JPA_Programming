package entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public abstract class Movie extends Item
{
    private String director;
    private String actor;
}