package com.personal.algoAlert.controllers;

import com.personal.algoAlert.services.QuestionService;
import com.personal.algoAlert.shared.dto.QuestionRecordReqDto;
import com.personal.algoAlert.shared.model.QuestionRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<QuestionRecords> getAllQuestionRecords(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionRecords> getQuestionRecordById(@PathVariable String id){
        QuestionRecords question = questionService.getQuestionById(id);
        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public QuestionRecords createUser(@RequestBody QuestionRecordReqDto quesRec) {
        return questionService.createQuestion(quesRec);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionRecords> updateQuestionRecord(@PathVariable String id, @RequestBody QuestionRecords updatedQuesRec){
        QuestionRecords updatedQuestion = questionService.updateQuestion(id, updatedQuesRec);
        return updatedQuestion != null ? ResponseEntity.ok(updatedQuestion) : ResponseEntity.notFound().build();
    }
}
