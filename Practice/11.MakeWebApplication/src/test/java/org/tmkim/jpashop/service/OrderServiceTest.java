package org.tmkim.jpashop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.tmkim.jpashop.domain.Address;
import org.tmkim.jpashop.domain.Member;
import org.tmkim.jpashop.domain.Order;
import org.tmkim.jpashop.domain.OrderStatus;
import org.tmkim.jpashop.domain.item.Book;
import org.tmkim.jpashop.domain.item.Item;
import org.tmkim.jpashop.exception.NotEnoughStockException;
import org.tmkim.jpashop.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
public class OrderServiceTest
{
    @PersistenceContext
    EntityManager em;
    
    @Autowired
    OrderService orderService;
    
    @Autowired
    OrderRepository orderRepository;
    
    @Test
    public void orderItem() throws Exception, NotEnoughStockException
    {
        //Given
        Member member = createMember();
        Item item = createBook("시골JPA", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;

        //When
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //Then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, item.getStockQuantity());
    }

    //재고 수량 초과 테스트
    @Test(expected = NotEnoughStockException.class)
    public void exceedOrderItem() throws Exception, NotEnoughStockException
    {
        //Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 1000, 10); //이름, 가격, 재고

        int orderCount = 11; //재고보다 많은 수량

        //When
        orderService.order(member.getId(), item.getId(), orderCount);

        //Then
        fail("재고 수량 부족 예외가 발생해야 함");
    }

    //주문 취소 테스트
    @Test
    public void cancelOrder() throws NotEnoughStockException
    {
        //Given
        Member member = createMember();
        Item item = createBook("시골 JPA", 1000, 10); //이름, 가격, 재고
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //When
        orderService.cancelOrder(orderId);

        //Then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL 이다", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
    }

    private Member createMember()
    {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity)
    {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }
}
