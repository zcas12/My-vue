package com.board.My.Board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishitems")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WishItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;      //주문 상품

    private int count;      //수량

    @Column(name = "wish_date")
    private LocalDateTime wishDate;

    public static WishItem createWish(Member member, Item item, int count) {
        WishItem wishItem = new WishItem();
        wishItem.setMember(member);
        wishItem.setItem(item);
        wishItem.setWishDate(LocalDateTime.now());
        wishItem.setCount(count);
        return wishItem;
    }
}
