package com.ahuiali.word.json;

import com.ahuiali.word.pojo.Word;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class WordJson extends JsonBase{

    private List<Word> words;

    public void create(Integer code, String message,List<Word> words){
        super.code = code;
        super.message = message;
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "WordJson{" +
                "words=" + words +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
