package com.board.My.Board.service;

import com.board.My.Board.domain.QnA;
import com.board.My.Board.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QnAService {

    private final QnARepository qnaRepository;

    @Transactional
    public Long join (QnA qna){
        qnaRepository.save(qna);
        return qna.getId();
    }

    public List<QnA> findQnA(){
        return qnaRepository.findAll();
    }

    public QnA findOne(Long qnaId){
        return qnaRepository.findOne(qnaId);
    }

    @Transactional
    public Long delete(Long qnaId){
        qnaRepository.delete(qnaId);
        return qnaId;
    }

    @Transactional
    public void update(Long id, String answer) {
        System.err.println("서비스1"+answer);
        QnA qna = qnaRepository.findOne(id);
        qna.setAnswer(answer);
        System.err.println("서비스2"+qna);
    }

}
