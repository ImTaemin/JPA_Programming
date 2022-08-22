package entity.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public abstract class Album extends Item
{
    private String artist;
    private String etc;
}
