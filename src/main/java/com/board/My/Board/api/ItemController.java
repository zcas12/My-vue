package com.board.My.Board.api;

import com.board.My.Board.domain.Item;
import com.board.My.Board.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/v1/InteriorList")
    public Result itemV1(){
        String category = "interior";
        List<Item> findItem = itemService.findCategory(category);
        return new Result(findItem);
    }

    @GetMapping("/api/v1/KitchenList")
    public Result itemV2(){
        String category = "kitchen";
        List<Item> findItem = itemService.findCategory(category);
        return new Result(findItem);
    }

    @GetMapping("/api/v1/BedroomList")
    public Result itemV3(){
        String category = "bedroom";
        List<Item> findItem = itemService.findCategory(category);
        return new Result(findItem);
    }

    @GetMapping(value = "/api/itemDetail/{id}")
    public Result findOne(@PathVariable("id") long id){
        Item findItem = itemService.findOne(id);
        return new Result(findItem);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
