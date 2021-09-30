package com.board.My.Board.service;

import com.board.My.Board.domain.*;
import com.board.My.Board.repository.ItemRepository;
import com.board.My.Board.repository.MemberRepository;
import com.board.My.Board.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     *주문
     **/
    @Transactional
    public Long order(String memberId, Long itemId, int count){
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),count);

        //주문 생성
        Order order =  Order.createOrder(member, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }
    
    //취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
    //검색
    public List<Order> findOrder(String id){
        return orderRepository.findOrder(id);
    }
    //검색
    public List<OrderItem> findOrderItem(String id){
        return orderRepository.findOrderItem(id);
    }

    public List<OrderItem> findOrderItem2() {return orderRepository.findOrderItem2();}

    @Transactional
    public void update(Long id, OrderStatus status) {
        Order order = orderRepository.findOne(id);
        System.err.println(order);
        order.setStatus(status);
    }
}
