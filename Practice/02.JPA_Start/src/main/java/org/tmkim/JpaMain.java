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
        
        //트랜잭션 - 획득
        EntityTransaction tx = em.getTransaction();

        try
        {
            tx.begin();
            logic(em); //비즈니스 로직 실행
            tx.commit();
        }
        catch (Exception e)
        {
            tx.rollback();
        }
        finally
        {
            em.close(); //엔티티 매니저 - 종료
        }
        emf.close(); //엔티티 매니저 팩토리 - 종료
    }
    
    //비즈니스 로직
    public static void logic(EntityManager em)
    {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        em.persist(member); //등록
        member.setAge(20); //수정
        Member findMember = em.find(Member.class, id); // 한 건 조회
        //목록 조회
        List<Member> members = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
        em.remove(em); //삭제
    }
}
