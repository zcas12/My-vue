package com.board.My.Board.controller;

import com.board.My.Board.domain.Member;
import com.board.My.Board.dto.LoginDto;
import com.board.My.Board.dto.TokenDto;
import com.board.My.Board.jwt.JwtFilter;
import com.board.My.Board.jwt.TokenProvider;
import com.board.My.Board.service.AuthService;
import com.board.My.Board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    // 로그인
    @PostMapping("/api/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto){

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getId(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        System.err.println("jwt==>"+jwt);
        Member findMember = memberService.findOne(loginDto.getId());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer" + jwt);

        return new ResponseEntity<>(new TokenDto(jwt,loginDto.getId(),findMember.getAuthority()), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public void hello(){
        System.out.println("hello");
    }
}
