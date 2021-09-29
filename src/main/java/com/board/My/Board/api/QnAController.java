package com.board.My.Board.api;

import com.board.My.Board.domain.QnA;
import com.board.My.Board.service.QnAService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QnAController {

    private final QnAService qnaService;
    
    //QnA 조회
    @GetMapping("/api/v1/QnA")
    public Result qnaV1(){
        List<QnA> findQnA = qnaService.findQnA();
        return new Result(findQnA);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    //QnA 저장
    @PostMapping("/api/v1/QnA")
    public CreateQnAResponse SaveQnAV1(@RequestBody QnA qna){
        Long id = qnaService.join(qna);
        return new CreateQnAResponse(id);
    }

   @Data
   static class CreateQnAResponse{
       private Long id;
       public CreateQnAResponse(Long id){
           this.id = id;
       }
   }
   
   //QnA 삭제
   @DeleteMapping("/api/v1/QnA/{id}")
   public DeleteQnAResponse deleteQnA(@PathVariable("id") long id) {
       Long ids = qnaService.delete(id);
       return new DeleteQnAResponse(ids);
    }

    @Data
    static class DeleteQnAResponse {
        private Long id;
        public DeleteQnAResponse(Long id){ this.id = id; }
    }

    //QnA 답변 등록
    @PutMapping("/api/v1/QnA/{id}")
    public UpdateAnsResponse updateAnswer(
            @PathVariable("id") Long id,
            @RequestBody UpdateAnsRequest request){

        qnaService.update(id, request.getAnswer());
        QnA findQnA = qnaService.findOne(id);

        return new UpdateAnsResponse(findQnA.getId(), findQnA.getAnswer());
    }

    @Data
    static class  UpdateAnsRequest {
        private String answer;
    }

    @Data
    @AllArgsConstructor
    static class UpdateAnsResponse {
        private Long id;
        private String answer;
    }


}
