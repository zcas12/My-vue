package com.board.My.Board.repository;

import com.board.My.Board.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(String id) {return em.find(Member.class, id);}

    public Member findMember(String id, String password) {
        return em.createQuery(
                "select m from Member m where m.id = :id and m.password = :password", Member.class)
                .setParameter("id", id)
                .setParameter("password", password)
                .getSingleResult();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m ", Member.class)
                .getResultList();
    }
}
