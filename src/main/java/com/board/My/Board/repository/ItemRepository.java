package com.board.My.Board.repository;

import com.board.My.Board.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @PersistenceContext
    private  final EntityManager em;

    public void save(Item item){
        if (item.getId() == null){
            em.persist(item);
        }else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findCategory(String category){
        return em.createQuery("select i from Item i where i.category = :category", Item.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i" , Item.class)
                .getResultList();
    }
}
