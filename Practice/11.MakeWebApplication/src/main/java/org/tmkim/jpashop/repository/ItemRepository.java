package org.tmkim.jpashop.repository;

import org.springframework.stereotype.Repository;
import org.tmkim.jpashop.domain.item.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository
{
    @PersistenceContext
    EntityManager em;

    public void save(Item item)
    {
        if (item.getId() == null)
            em.persist(item);
        else
            em.merge(item);
    }

    public Item findOne(Long id)
    {
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }
}
