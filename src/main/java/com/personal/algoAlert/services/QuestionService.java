package com.personal.algoAlert.services;

import com.personal.algoAlert.shared.dto.QuestionRecordReqDto;
import com.personal.algoAlert.shared.helpers.DateTimeConverter;
import com.personal.algoAlert.shared.model.QuestionRecords;
import com.personal.algoAlert.system.repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionsRepository questionsRepository;

    public List<QuestionRecords> getAllQuestions() {
        // You can perform some operations here if necessary
        return questionsRepository.findAll();
    }

    // Business logic for retrieving a question by ID
    public QuestionRecords getQuestionById(String id) {
        return questionsRepository.findById(id).orElse(null);
    }

    // Business logic for creating a new question
    public QuestionRecords createQuestion(QuestionRecordReqDto questionReq) {
        // Perform operations before saving
        // Example: setting a default value if null


        if (questionReq.getQuesComment() == null) {
            questionReq.setQuesComment("No comment provided");
        }

        // Calculate date values here
        String firstAttemptDate = DateTimeConverter.currentDateTime();
        String lastAttemptDate = DateTimeConverter.currentDateTime();
        String nextAttemptDate = DateTimeConverter.addDaysToDateTime(lastAttemptDate, questionReq.getQuesRepeatFreq());

        // Convert DTO to domain object
        QuestionRecords question = new QuestionRecords(
                null,
                questionReq.getTopic(),
                questionReq.getQuesName(),
                questionReq.getQuesDifficulty(),
                questionReq.getQuesPlatform(),
                questionReq.isQuesSolved(),
                questionReq.getQuesLink(),
                questionReq.getQuesComment(),
                questionReq.getQuesSolutionLink(),
                questionReq.getQuesRepeatFreq(),
                firstAttemptDate,
                lastAttemptDate,
                nextAttemptDate
        );

        return questionsRepository.save(question);
    }

    // Business logic for updating a question
    public QuestionRecords updateQuestion(String id, QuestionRecords updatedQuestion) {
        return questionsRepository.findById(id).map(question -> {
            // Perform necessary operations before updating
            question.setQuesName(updatedQuestion.getQuesName());
            question.setQuesSolved(updatedQuestion.isQuesSolved());
            // ... Other fields to update
            return questionsRepository.save(question);
        }).orElse(null);
    }

    private String calculateLastAttemptDate(QuestionRecordReqDto dto) {
        // Your logic to calculate last attempt date
        return "2024-01-02"; // Example
    }


}
