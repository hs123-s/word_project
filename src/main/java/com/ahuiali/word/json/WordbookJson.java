package com.ahuiali.word.json;

import com.ahuiali.word.pojo.Wordbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class WordbookJson extends JsonBase {

    private List<Wordbook> wordbooks;

    private Wordbook wordbook;

    public Wordbook getWordbook() {
        return wordbook;
    }

    public void setWordbook(Wordbook wordbook) {
        this.wordbook = wordbook;
    }

    @Override
    public Integer getCode() {
        return super.getCode();
    }

    public List<Wordbook> getWordbooks() {
        return wordbooks;
    }

    public WordbookJson() {}
    public void create(Integer code, String message, List<Wordbook> wordbooks) {

        super.code = code;
        super.message = message;
        this.wordbooks = wordbooks;
    }

    public void create(Integer code, String message, Wordbook wordbook) {

        super.code = code;
        super.message = message;
        this.wordbook = wordbook;
    }

    public void setWordbooks(List<Wordbook> wordbooks) {
        this.wordbooks = wordbooks;
    }

    @Override
    public String toString() {
        return "WordbookJson{" +
                "wordbooks=" + wordbooks +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
