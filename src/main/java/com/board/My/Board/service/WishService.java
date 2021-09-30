package com.board.My.Board.service;

import com.board.My.Board.domain.Item;
import com.board.My.Board.domain.Member;
import com.board.My.Board.domain.Order;
import com.board.My.Board.domain.WishItem;
import com.board.My.Board.repository.ItemRepository;
import com.board.My.Board.repository.MemberRepository;
import com.board.My.Board.repository.OrderRepository;
import com.board.My.Board.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * wish
     */
    @Transactional
    public Long wish(String memberId, Long itemId, int count){
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);
        //wish 생성
        WishItem wishItem =  WishItem.createWish(member, item, count);

        //wish 저장
        wishRepository.save(wishItem);
        return wishItem.getId();
    }

    public Long findCheck(Long itemId, String memberId) {
        WishItem wishItem = wishRepository.findCheck(itemId, memberId);
        return wishItem.getId();
    }

    public List<WishItem> findWish(String id) {
        return wishRepository.findWish(id);
    }

    public Long delete(long id) {
        wishRepository.delete(id);
        return id;
    }
}
