package com.personal.algoAlert.controllers;

import com.personal.algoAlert.services.QuestionService;
import com.personal.algoAlert.shared.dto.QuestionRecordReqDto;
import com.personal.algoAlert.shared.model.QuestionRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public List<QuestionRecords> getAllQuestionRecords(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/filtered")
    public Map<String, Object> getAllQuestionRecords(@RequestParam String firebase_uid,
                                                     @RequestParam(required = false) String searchQuery,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size){
        // Default values for pagination if not provided
        int currentPage = (page != null) ? page : 0;
        int pageSize = (size != null) ? size : 10;

        return questionService.getAllQuestions(firebase_uid, searchQuery, currentPage, pageSize);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestionRecord(@PathVariable String id){
        boolean isDeleted = questionService.deleteQuestion(id);
        return isDeleted ? ResponseEntity.ok("Deleted " + id + " successfully!") : ResponseEntity.notFound().build();
    }
}
