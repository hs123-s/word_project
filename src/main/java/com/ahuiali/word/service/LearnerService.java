package com.ahuiali.word.service;

import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.LearnerJson;
import com.ahuiali.word.pojo.Learner;

public interface LearnerService {

    JsonBase addLearn(Learner learner);

    LearnerJson queryLearner(Learner learner);

    LearnerJson queryLearnerByEmail(String email);

    LearnerJson queryLearnerByNickname(String nickname);

    JsonBase register(Learner learner);

    JsonBase confirm(String token);

    JsonBase sentEmailAgain(String email);

    JsonBase findPassword(String email);

    JsonBase updatePassword(String email, String password);
}
