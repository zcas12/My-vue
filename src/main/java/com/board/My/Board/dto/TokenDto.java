package com.board.My.Board.dto;

import com.board.My.Board.domain.Authority;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;
    private String id;
    private Authority authority;
}