package com.board.My.Board.domain;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Member {

    @Id @Column(name = "member_id")
    private String id;

    @NonNull
    private String password;

    @NonNull
    private String name;
    private String email;

    //@Embedded
    private String address;
    private String address2;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
