package com.personal.algoAlert.services;

import com.personal.algoAlert.shared.dto.QuestionRecordReqDto;
import com.personal.algoAlert.shared.helpers.DateTimeConverter;
import com.personal.algoAlert.shared.model.QuestionRecords;
import com.personal.algoAlert.system.repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    @Autowired
    private QuestionsRepository questionsRepository;

    private final MongoTemplate mongoTemplate;

    public QuestionService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<QuestionRecords> getAllQuestions() {
        return questionsRepository.findAll();
    }

    public Map<String, Object> getAllQuestions(String firebaseUid, String searchQuery, int page, int size) {
        Query countQuery = new Query();
        countQuery.addCriteria(Criteria.where("firebase_uid").is(firebaseUid));
        long totalCount = mongoTemplate.count(countQuery, QuestionRecords.class);

        //query to get filtered data
        Query query = new Query();
        query.addCriteria(Criteria.where("firebase_uid").is(firebaseUid));

        //add search query if search string is present
        if(searchQuery != null && !searchQuery.isEmpty()){
           query.addCriteria(Criteria.where("quesName").regex(searchQuery, "i"));
        }

        //add sorting by 'quesLastAttemptDate', descending (latest first)
        query.with(Sort.by(Sort.Direction.DESC, "quesLastAttemptDate"));

        //pagination
        query.skip(page * size).limit(size);

        //execute the query
        List<QuestionRecords> questions = mongoTemplate.find(query, QuestionRecords.class);

        //prepare response
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("response_data", questions);
        responseData.put("totalCount", totalCount);

        return responseData;
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
                questionReq.getFirebase_uid(),
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

    public Map<String, Object> questionsToAttemptToday(String firebaseUid, String searchQuery, int page, int size){
        LocalDate today = LocalDate.now();
        ZonedDateTime startOfDay = today.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endOfDay = today.plusDays(1).atStartOfDay(ZoneId.systemDefault());

        Date startDate = Date.from(startOfDay.toInstant());
        Date endDate = Date.from(endOfDay.toInstant());

        Query countQuery = new Query();
        countQuery.addCriteria(Criteria.where("firebase_uid").is(firebaseUid));
        long totalCount = mongoTemplate.count(countQuery, QuestionRecords.class);

        //query to get filtered data
        //2024-09-11 16:58:10
        Query query = new Query();
        query
                .addCriteria(Criteria.where("firebase_uid").is(firebaseUid))
                .addCriteria(Criteria.where("quesNextAttemptDate").gte(startDate).lte(endDate));

        //add search query if search string is present
        if(searchQuery != null && !searchQuery.isEmpty()){
            query.addCriteria(Criteria.where("quesName").regex(searchQuery, "i"));
        }

        //add sorting by 'quesLastAttemptDate', descending (latest first)
        query.with(Sort.by(Sort.Direction.DESC, "quesLastAttemptDate"));

        //pagination
        query.skip(page * size).limit(size);

        //execute the query
        List<QuestionRecords> questions = mongoTemplate.find(query, QuestionRecords.class);

        //prepare response
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("response_data", questions);
        responseData.put("totalCount", totalCount);

        return responseData;
    }


}
