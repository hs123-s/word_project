package com.ahuiali.word.json;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * json的公共类
 */
@Component
@Scope("prototype")
public class JsonBase {

    //返回码
    Integer code;

    //返回信息
    String message;

    //设置信息
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
