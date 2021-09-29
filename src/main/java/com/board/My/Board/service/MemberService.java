package com.board.My.Board.service;

import com.board.My.Board.domain.Authority;
import com.board.My.Board.domain.Item;
import com.board.My.Board.domain.Member;
import com.board.My.Board.dto.MemberUpdateDto;
import com.board.My.Board.jwt.TokenProvider;
import com.board.My.Board.repository.MemberRepository;
import com.board.My.Board.repository.UserRepository;
import com.board.My.Board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public String join (Member member){
        //빌더 패턴의 장점
        member.setAuthority(Authority.valueOf("ROLE_USER"));
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(member);
        return member.getId();
    }

    public Member findOne(String id){
        return memberRepository.findOne(id);
    }

    public Member findMember(String id, String password){
        return memberRepository.findMember(id, password);
    }

    @Transactional
    public void update(MemberUpdateDto memberUpdateDto) {
        String id = memberUpdateDto.getId();
        Member member = memberRepository.findOne(id);
        member.setPassword(passwordEncoder.encode(memberUpdateDto.getNewPassword()));
        member.setEmail(memberUpdateDto.getEmail());
        member.setAddress(memberUpdateDto.getAddress());
        member.setAddress2(memberUpdateDto.getAddress2());
    }

    public List<Member> findMemberList() {
        return memberRepository.findAll();
    }
}
