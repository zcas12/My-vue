package com.board.My.Board.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull
    @Size(min=3, max=50)
    private String id;

    @NonNull
    @Size(min = 3, max = 100)
    private String password;
}
