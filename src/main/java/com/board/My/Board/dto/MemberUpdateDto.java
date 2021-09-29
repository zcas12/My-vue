package com.board.My.Board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {
    @NotNull
    @Size(min=3, max=50)
    private String id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    @Size(min = 3, max = 100)
    private String NewPassword;

    @NonNull
    @Size(min = 3, max = 100)
    private String email;

    private String address;
    private String address2;
}
