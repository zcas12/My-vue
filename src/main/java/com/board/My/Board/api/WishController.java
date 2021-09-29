package com.board.My.Board.api;

import com.board.My.Board.domain.*;
import com.board.My.Board.service.ItemService;
import com.board.My.Board.service.QnAService;
import com.board.My.Board.service.WishService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;
    @GetMapping("/api/wish/{id}")
    public List<wishItemResponse> orderitem(@PathVariable("id") String id){
        List<WishItem> wishList = wishService.findWish(id);
        List<wishItemResponse> result = wishList.stream()
                .map(o -> new wishItemResponse(o))
                .collect(Collectors.toList());
        return result;
    }
    @Data
    static class wishItemResponse {
        private Long wishId;
        private String name;
        private int price;
        private int count;
        private LocalDateTime wishDate;
        public wishItemResponse(WishItem wishItem){
            wishId = wishItem.getId();
            name = wishItem.getItem().getName();
            price = wishItem.getItem().getPrice();
            count = wishItem.getCount();
            wishDate = wishItem.getWishDate();
        }
    }
    //wish 등록
    @PostMapping("/api/wish")
    public CreateWishesponse wishCreate(@RequestBody CreateWishRequest req){
        int count = req.count;
        Long itemId = req.itemId;
        String memberId = req.memberId;
        Long id = wishService.wish(memberId, itemId, count);
        return new CreateWishesponse(id);
    }
    @Data
    static class CreateWishRequest{
        private String memberId;
        private Long itemId;
        private int count;
        public CreateWishRequest(String memberId, Long itemId, int count){
            this.memberId = memberId;
            this.itemId = itemId;
            this.count = count;
        }
    }
    @Data
    static class CreateWishesponse{
        private Long id;
        public CreateWishesponse(Long id){
            this.id = id;
        }
    }
    @GetMapping("/api/wishCheck/{itemId}/{memberId}")
    public Result wishCheck(
            @PathVariable("itemId") long itemId,
            @PathVariable("memberId") String memberId ){
        Long CheckId = wishService.findCheck(itemId,memberId);

        return new Result(CheckId);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
