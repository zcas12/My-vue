package com.board.My.Board.repository;


import com.board.My.Board.domain.QnA;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QnARepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(QnA qna){
        em.persist(qna);
    }

    public QnA findOne(Long id){
        return em.find(QnA.class, id);
    }

    public List<QnA> findAll(){
        return em.createQuery("select q from QnA q", QnA.class)
                .getResultList();
    }

    public List<QnA> findByTitle(String title){
        return em.createQuery("select q from QnA q where q.title = :title",QnA.class)
                .setParameter("title", title)
                .getResultList();
    }

    public void delete(Long id){
        QnA qna = em.find(QnA.class, id);
        em.remove(qna);
    }
}
