package com.board.My.Board.repository;

import com.board.My.Board.domain.WishItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WishRepository {


    private final EntityManager em;

    public void save(WishItem wishItem){ em.persist(wishItem); }

    public WishItem findCheck(Long itemId, String memberId) {
        return em.createQuery("select w from WishItem w"+
                " where w.member.id = :memberId"+
                " and w.item.id = :itemId  order by w.wishDate desc ", WishItem.class )
                .setParameter("memberId", memberId)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }

    public List<WishItem> findWish(String id) {
        return em.createQuery("select w from WishItem w join w.member m" +
                " where m.id = :memberId order by w.wishDate desc ",WishItem.class)
                .setParameter("memberId", id)
                .setMaxResults(1000)
                .getResultList();

    }


    public void delete(long id) {
        WishItem wishItem = em.find(WishItem.class,id);
        System.err.println(wishItem);
        em.remove(wishItem);
    }
}
