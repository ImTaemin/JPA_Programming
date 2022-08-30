package org.tmkim.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tmkim.jpashop.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long>
{
}

/*상품 리포지토리 리팩토링 전*/
//@Repository
//public class ItemRepository
//{
//    @PersistenceContext
//    EntityManager em;
//
//    public void save(Item item)
//    {
//        if (item.getId() == null)
//            em.persist(item);
//        else
//            em.merge(item);
//    }
//
//    public Item findOne(Long id)
//    {
//        return em.find(Item.class, id);
//    }
//
//    public List<Item> findAll(){
//        return em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
//    }
//}
