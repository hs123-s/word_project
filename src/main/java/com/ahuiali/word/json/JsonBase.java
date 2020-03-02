package com.ahuiali.word.json;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JsonBase {

    Integer code;

    String message;

    public void create(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonBase{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
