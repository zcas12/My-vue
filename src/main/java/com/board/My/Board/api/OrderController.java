package com.board.My.Board.api;

import com.board.My.Board.domain.*;
import com.board.My.Board.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문조회 V1
    @GetMapping("/api/order/{id}")
    public List<Order> orderList(@PathVariable("id") String id){
        List<Order> orderList = orderService.findOrder(id);
        for (Order order : orderList){
            order.getMember().getAddress();
            order.getOrderItems().getClass();
        }
        return orderList;
    }
    // 주문조회 V2
    @GetMapping("/api/orderItem/{id}")
    public List<orderItemResponse> orderitem(@PathVariable("id") String id){
        List<OrderItem> orderList = orderService.findOrderItem(id);
        List<orderItemResponse> result = orderList.stream()
                .map(o -> new orderItemResponse(o))
                .collect(Collectors.toList());
        return result;
    }

    //주문하기(CREATE)
    @PostMapping("/api/order")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest req){

        int count = req.count;
        Long itemId = req.itemId;
        String memberId = req.memberId;
        Long id = orderService.order(memberId, itemId, count);

        return new CreateOrderResponse(id);
    }
    //주문 요청 REQUEST
    @Data
    static class CreateOrderRequest{
        private String memberId;
        private Long itemId;
        private int count;
        public CreateOrderRequest(String memberId, Long itemId, int count){
            this.memberId = memberId;
            this.itemId = itemId;
            this.count = count;
        }
    }
    //주문 RESPONSE
    @Data
    static class CreateOrderResponse{
        private Long id;
        public CreateOrderResponse(Long id){
            this.id = id;
        }
    }

    @GetMapping("/api/admin/order")
    public List<orderItemResponse> adminOrderItem(){

        List<OrderItem> orderList = orderService.findOrderItem2();
        List<orderItemResponse> result = orderList.stream()
                .map(o -> new orderItemResponse(o))
                .collect(Collectors.toList());
        return result;
    }

    //주문목록 response
    @Data
    static class orderItemResponse {
        private Long orderId;
        private String name;
        private int orderPrice; //주문 가격
        private int count;      //주문 수량
        private LocalDateTime orderDate;
        private OrderStatus status;
        public orderItemResponse(OrderItem orderItem){
            orderId = orderItem.getId();
            name = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
            orderDate = orderItem.getOrder().getOrderDate();
            status = orderItem.getOrder().getStatus();
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
