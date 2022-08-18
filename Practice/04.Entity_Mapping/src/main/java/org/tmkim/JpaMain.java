package org.tmkim;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain
{
    public static void main(String[] args)
    {
        //엔티티 매니저 팩토리 - 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        //엔티티 매니저 - 생성
        EntityManager em = emf.createEntityManager();
        
        em.close(); //엔티티 매니저 - 종료
        emf.close(); //엔티티 매니저 팩토리 - 종료
    }
}
