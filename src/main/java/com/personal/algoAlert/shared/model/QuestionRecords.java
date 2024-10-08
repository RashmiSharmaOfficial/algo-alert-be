package com.personal.algoAlert.shared.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//this is not dto ->  this is data model, it represents the structure of data stored in
//db, mapped to a database collection
@Document(collection = "questionrecords")
public class QuestionRecords {
    @Id
    private String id;

    private String firebase_uid;
    private String[] topic;
    private String quesName;
    private String quesDifficulty;
    private String quesPlatform;
    private boolean quesSolved;
    private String quesLink;
    private String quesComment;
    private String quesSolutionLink;
    private int quesRepeatFreq;
    private String quesFirstAttemptDate;
    private String quesLastAttemptDate;
    private String quesNextAttemptDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirebase_uid() {
        return firebase_uid;
    }

    public void setFirebase_uid(String firebase_uid) {
        this.firebase_uid = firebase_uid;
    }

    public String[] getTopic() {
        return topic;
    }

    public void setTopic(String[] topic) {
        this.topic = topic;
    }

    public String getQuesName() {
        return quesName;
    }

    public void setQuesName(String quesName) {
        this.quesName = quesName;
    }

    public String getQuesDifficulty() {
        return quesDifficulty;
    }

    public void setQuesDifficulty(String quesDifficulty) {
        this.quesDifficulty = quesDifficulty;
    }

    public String getQuesPlatform() {
        return quesPlatform;
    }

    public void setQuesPlatform(String quesPlatform) {
        this.quesPlatform = quesPlatform;
    }

    public boolean isQuesSolved() {
        return quesSolved;
    }

    public void setQuesSolved(boolean quesSolved) {
        this.quesSolved = quesSolved;
    }

    public String getQuesLink() {
        return quesLink;
    }

    public void setQuesLink(String quesLink) {
        this.quesLink = quesLink;
    }

    public String getQuesComment() {
        return quesComment;
    }

    public void setQuesComment(String quesComment) {
        this.quesComment = quesComment;
    }

    public String getQuesSolutionLink() {
        return quesSolutionLink;
    }

    public void setQuesSolutionLink(String quesSolutionLink) {
        this.quesSolutionLink = quesSolutionLink;
    }

    public int getQuesRepeatFreq() {
        return quesRepeatFreq;
    }

    public void setQuesRepeatFreq(int quesRepeatFreq) {
        this.quesRepeatFreq = quesRepeatFreq;
    }

    public String getQuesFirstAttemptDate() {
        return quesFirstAttemptDate;
    }

    public void setQuesFirstAttemptDate(String quesFirstAttemptDate) {
        this.quesFirstAttemptDate = quesFirstAttemptDate;
    }

    public String getQuesLastAttemptDate() {
        return quesLastAttemptDate;
    }

    public void setQuesLastAttemptDate(String quesLastAttemptDate) {
        this.quesLastAttemptDate = quesLastAttemptDate;
    }

    public String getQuesNextAttemptDate() {
        return quesNextAttemptDate;
    }

    public void setQuesNextAttemptDate(String quesNextAttemptDate) {
        this.quesNextAttemptDate = quesNextAttemptDate;
    }

    public QuestionRecords(String id, String firebase_uid, String[] topic, String quesName, String quesDifficulty, String quesPlatform, boolean quesSolved, String quesLink, String quesComment, String quesSolutionLink, int quesRepeatFreq, String quesFirstAttemptDate, String quesLastAttemptDate, String quesNextAttemptDate) {
        this.id = id;
        this.firebase_uid = firebase_uid;
        this.topic = topic;
        this.quesName = quesName;
        this.quesDifficulty = quesDifficulty;
        this.quesPlatform = quesPlatform;
        this.quesSolved = quesSolved;
        this.quesLink = quesLink;
        this.quesComment = quesComment;
        this.quesSolutionLink = quesSolutionLink;
        this.quesRepeatFreq = quesRepeatFreq;
        this.quesFirstAttemptDate = quesFirstAttemptDate;
        this.quesLastAttemptDate = quesLastAttemptDate;
        this.quesNextAttemptDate = quesNextAttemptDate;
    }
}
