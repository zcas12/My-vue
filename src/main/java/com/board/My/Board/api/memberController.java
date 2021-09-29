package com.board.My.Board.api;

import com.board.My.Board.domain.Member;
import com.board.My.Board.dto.MemberUpdateDto;
import com.board.My.Board.jwt.TokenProvider;
import com.board.My.Board.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class memberController {
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;

/*    @PostMapping("/api/login")
    public Member login(@RequestBody Member member){
        String id = member.getId();
        String password = member.getPassword();
        Member findMember = memberService.findMember(id, password);
        return findMember;
    }*/

    @PostMapping("/api/auth/signUp")
    public CreateMemberResponse signUp(@RequestBody Member member){
        String id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberResponse{
        private String id;
        public CreateMemberResponse(String id){
            this.id = id;
        }
    }

    @GetMapping(value = "/api/MemberCheck/{id}")
    public Result findOne(@PathVariable("id") String id){
        Member findMember = memberService.findOne(id);
        return new Result(findMember);
    }
    @PostMapping(value = "/api/PasswordCheck")
    public boolean PasswordCheck(@RequestBody Member member){
        String id = member.getId();
        String password = member.getPassword();
        Member findMember = memberService.findOne(id);
        String encodedPassword = findMember.getPassword();
        boolean result = false;
        if(passwordEncoder.matches(password, encodedPassword)){
            result = true;
        }else if(!passwordEncoder.matches(password, encodedPassword )){
            result = false;
        }
        return result;
    }
    @PutMapping(value = "/api/member")
    public UpdateAnsResponse MemberUpdate(@RequestBody MemberUpdateDto memberUpdateDto){
        memberService.update(memberUpdateDto);
        Member findMember = memberService.findOne(memberUpdateDto.getId());
        return new UpdateAnsResponse(findMember.getId());
    }
    @Data
    @AllArgsConstructor
    static class UpdateAnsResponse {
        private String id;
    }

    @GetMapping(value = "/api/myInfo/{id}")
    public myInfoResponse myInfo(@PathVariable("id") String id){
        System.err.println(id);
        Member findMember = memberService.findOne(id);
        System.err.println(findMember);
        return new myInfoResponse(findMember.getId(), findMember.getName(), findMember.getEmail(), findMember.getAddress(), findMember.getAddress2());
    }

    @Data
    @AllArgsConstructor
    static class myInfoResponse {
        private String id;
        private String name;
        private String email;
        private String address;
        private String address2;
    }
    @GetMapping(value = "/api/admin/member")
    public Result memberList(){
        List<Member> findMember = memberService.findMemberList();
        return new Result(findMember);
    }



    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
