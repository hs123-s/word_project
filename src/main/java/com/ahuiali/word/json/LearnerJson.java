package com.ahuiali.word.json;

import com.ahuiali.word.pojo.Learner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 用于存放用户的json
 */
@Component
@Scope("prototype")
public class LearnerJson extends JsonBase {
    Learner learner;

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    @Override
    public Integer getCode() {
        return super.getCode();
    }

    @Override
    public void setCode(Integer code) {
        super.setCode(code);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
