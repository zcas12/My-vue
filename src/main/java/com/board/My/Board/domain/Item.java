package com.board.My.Board.domain;

import com.board.My.Board.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    private String category;

    private String name;

    private int price;

    private int stock;


    /**
     * stock 증가
     */
     public void addStock(int quantity){
         this.stock += quantity;
     }

    /**
     * stock 증가
     */
    public void removeStock(int quantity){
        int restStock = this.stock - quantity;
        if (restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stock = restStock;
    }
}
