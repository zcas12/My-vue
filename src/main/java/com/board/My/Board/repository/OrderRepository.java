package com.board.My.Board.repository;

import com.board.My.Board.domain.Order;
import com.board.My.Board.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    public List<Order> findOrder(String id){

        return em.createQuery("select o from Order o join o.member m" +
                " where m.id = :memberId" , Order.class)
                .setParameter("memberId", id)
                .setMaxResults(1000) // 최대 1000건
                .getResultList();
    }
    public List<OrderItem> findOrderItem(String id){

        return em.createQuery("select o1 from OrderItem o1 join o1.order o join o.member m" +
                        " where m.id = :memberId" , OrderItem.class)
                .setParameter("memberId", id)
                .setMaxResults(1000) // 최대 1000건
                .getResultList();
    }

    public List<OrderItem> findOrderItem2() {
        return em.createQuery("select o1 from OrderItem o1 join o1.order o join o.member m", OrderItem.class)
                .setMaxResults(1000) // 최대 1000건
                .getResultList();
    }
}
