package com.board.My.Board.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class QnA {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_id")
    private Long id;

    private String title;

    private String content;

    private String name;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date date;

    private String answer;

    @Builder
    public QnA(Long id, String title, String content, String name, Date date, String answer){
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.date = date;
        this.answer = answer;
    }

}
