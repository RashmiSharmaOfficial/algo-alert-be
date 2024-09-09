package com.personal.algoAlert.system.repositories;

import com.personal.algoAlert.shared.model.QuestionRecords;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionsRepository extends MongoRepository<QuestionRecords, String> {
}

////package com.personal.codingPractice.mongodb.repository;
////
////import org.springframework.data.mongodb.repository.MongoRepository;
////import org.springframework.data.mongodb.repository.Query;
////
////import java.util.List;
////
////public interface ItemRepository extends MongoRepository<GroceryItem, String> {
////    @Query("{name:'?0'}")
////    GroceryItem findItemByName(String name);
////
////    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
////    List<GroceryItem> findAll(String category);
////
////    public long count();
////}