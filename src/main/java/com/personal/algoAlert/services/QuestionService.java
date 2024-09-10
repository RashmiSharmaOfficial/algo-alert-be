package com.personal.algoAlert.services;

import com.personal.algoAlert.shared.dto.QuestionRecordReqDto;
import com.personal.algoAlert.shared.helpers.DateTimeConverter;
import com.personal.algoAlert.shared.model.QuestionRecords;
import com.personal.algoAlert.system.repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//            String lastAttemptDate = DateTimeConverter.currentDateTime();
            String nextAttemptDate = DateTimeConverter.addDaysToDateTime(updatedQuestion.getQuesLastAttemptDate(), updatedQuestion.getQuesRepeatFreq());
            question.setId(updatedQuestion.getId());
            question.setTopic(updatedQuestion.getTopic());
            question.setQuesName(updatedQuestion.getQuesName());
            question.setQuesDifficulty(updatedQuestion.getQuesDifficulty());
            question.setQuesSolved(updatedQuestion.isQuesSolved());
            question.setQuesLink(updatedQuestion.getQuesLink());
            question.setQuesComment(updatedQuestion.getQuesComment());
            question.setQuesSolutionLink(updatedQuestion.getQuesSolutionLink());
            question.setQuesRepeatFreq(updatedQuestion.getQuesRepeatFreq());
            question.setQuesFirstAttemptDate(updatedQuestion.getQuesFirstAttemptDate());
            question.setQuesLastAttemptDate(updatedQuestion.getQuesLastAttemptDate());
            question.setQuesNextAttemptDate(nextAttemptDate);

            // Convert DTO to domain object
//            QuestionRecords questionReq = new QuestionRecords(
//                    updatedQuestion.getId(),
//                    updatedQuestion.getTopic(),
//                    updatedQuestion.getQuesName(),
//                    updatedQuestion.getQuesDifficulty(),
//                    updatedQuestion.getQuesPlatform(),
//                    updatedQuestion.isQuesSolved(),
//                    updatedQuestion.getQuesLink(),
//                    updatedQuestion.getQuesComment(),
//                    updatedQuestion.getQuesSolutionLink(),
//                    updatedQuestion.getQuesRepeatFreq(),
//                    updatedQuestion.getQuesFirstAttemptDate(),
//                    updatedQuestion.getQuesLastAttemptDate(),
//                    nextAttemptDate
//            );

            return questionsRepository.save(question);
        }).orElse(null);
    }

    public boolean deleteQuestion(String id){
        return Boolean.TRUE.equals(questionsRepository.findById(id).map(question -> {
            questionsRepository.deleteById(question.getId());
            return true;
        }).orElse(null));
    }


    private String calculateLastAttemptDate(QuestionRecordReqDto dto) {
        // Your logic to calculate last attempt date
        return "2024-01-02"; // Example
    }


}
