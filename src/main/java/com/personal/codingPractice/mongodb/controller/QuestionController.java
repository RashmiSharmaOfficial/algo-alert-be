package com.personal.codingPractice.mongodb.controller;

import com.personal.codingPractice.mongodb.model.QuestionRecords;
import com.personal.codingPractice.mongodb.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionsRepository questionsRepository;

    @GetMapping
    public List<QuestionRecords> getAllQuestionRecords(){
        return questionsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionRecords> getQuestionRecordById(@PathVariable String id){
        return questionsRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public QuestionRecords createUser(@RequestBody QuestionRecords quesRec) {
        return questionsRepository.save(quesRec);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionRecords> updateQuestionRecord(@PathVariable String id, @RequestBody QuestionRecords updatedQuesRec){
        return questionsRepository.findById(id).map(ques -> {
            ques.setQuesDifficulty(updatedQuesRec.getQuesDifficulty());
            ques.setQuesLink(updatedQuesRec.getQuesLink());
            ques.setQuesName(updatedQuesRec.getQuesName());
            ques.setQuesPlatform(updatedQuesRec.getQuesPlatform());
            ques.setQuesSolved(updatedQuesRec.isQuesSolved());
            ques.setQuesRepeatFreq(updatedQuesRec.getQuesRepeatFreq());
            ques.setQuesComment(updatedQuesRec.getQuesComment());
            ques.setQuesFirstAttemptDate(updatedQuesRec.getQuesFirstAttemptDate());
            ques.setQuesLastAttemptDate(updatedQuesRec.getQuesLastAttemptDate());
            ques.setQuesNextAttemptDate(updatedQuesRec.getQuesNextAttemptDate());
            ques.setTopic(updatedQuesRec.getTopic());
            return ResponseEntity.ok(questionsRepository.save(ques));
        }).orElse(ResponseEntity.notFound().build());
    }
}
