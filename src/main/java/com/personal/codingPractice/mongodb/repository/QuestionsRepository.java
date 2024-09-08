package com.personal.codingPractice.mongodb.repository;

import com.personal.codingPractice.mongodb.model.QuestionRecords;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionsRepository extends MongoRepository<QuestionRecords, String> {
}
