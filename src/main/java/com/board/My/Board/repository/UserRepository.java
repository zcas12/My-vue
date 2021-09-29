package com.board.My.Board.repository;

import com.board.My.Board.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Member,String> {
    Optional<Member> findById(String id);
}
