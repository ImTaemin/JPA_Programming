package org.tmkim.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tmkim.jpashop.domain.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>
{
    List<Member> findByName(String name);
}

/*회원 리포지토리 리팩토링 전*/
//@Repository
//public class MemberRepository
//{
//    @PersistenceContext
//    EntityManager em;
//
//    //회원 엔티티 저장(영속화)
//    public void save(Member member)
//    {
//        em.persist(member);
//    }
//
//    //회원 식별자로 회원 엔티티를 조회
//    public Member findOne(Long id)
//    {
//        return em.find(Member.class, id);
//    }
//
//    public List<Member> findAll()
//    {
//        return em.createQuery("SELECT m FROM Member m", Member.class)
//                .getResultList();
//    }
//
//    //JPQL을 이용해 이름(name)으로 회원 엔티티를 조회한다.
//    public List<Member> findByName(String name)
//    {
//        return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//    }
//}
