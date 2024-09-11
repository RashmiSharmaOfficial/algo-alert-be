package com.personal.algoAlert.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@Builder
@Repository
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionRecordReqDto {
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
}
