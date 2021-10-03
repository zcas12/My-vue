package com.board.My.Board.api;

import com.board.My.Board.domain.Category;
import com.board.My.Board.domain.Item;
import com.board.My.Board.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    //인테리어 카테고리 리스트
    @GetMapping("/api/v1/InteriorList")
    public Result itemV1(){
        List<Item> findItem = itemService.findCategory(Category.interior);
        return new Result(findItem);
    }

    // 키친 카테고리 리스트
    @GetMapping("/api/v1/KitchenList")
    public Result itemV2(){
        List<Item> findItem = itemService.findCategory(Category.kitchen);
        return new Result(findItem);
    }

    //침실 카테고리 리스트
    @GetMapping("/api/v1/BedroomList")
    public Result itemV3(){
        List<Item> findItem = itemService.findCategory(Category.bedroom);
        return new Result(findItem);
    }

    // 제품 상세 보기
    @GetMapping(value = "/api/itemDetail/{id}")
    public Result findOne(@PathVariable("id") long id){
        Item findItem = itemService.findOne(id);
        return new Result(findItem);
    }

    @PostMapping("/api/admin/item")
    public CreateItemResponse createItem(@RequestBody Item item){

        Long id = itemService.join(item);
        return new CreateItemResponse(id);
    }
    //아이템등록 REQUEST
    @Data
    static class CreateItemRequest{
        private String name;
        private int price;
        private int stock;
        private Category category;
        public CreateItemRequest(String name, int price, int stock, Category category){
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.category = category;
        }
    }
    //아이템등록 RESPONSE
    @Data
    static class CreateItemResponse{
        private Long id;
        public CreateItemResponse(Long id){
            this.id = id;
        }
    }
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
