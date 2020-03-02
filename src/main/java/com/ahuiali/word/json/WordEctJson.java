package com.ahuiali.word.json;

import com.ahuiali.word.pojo.WordEct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class WordEctJson extends JsonBase {

    private List<WordEct> wordEctList;

    private WordEct wordEct;

    public WordEct getWordEct() {
        return wordEct;
    }

    public void setWordEct(WordEct wordEct) {
        this.wordEct = wordEct;
    }

    public List<WordEct> getWordEctList() {
        return wordEctList;
    }

    public void setWordEctList(List<WordEct> wordEctList) {
        this.wordEctList = wordEctList;
    }

    @Override
    public String toString() {
        return "WordEctJson{" +
                "wordEctList=" + wordEctList +
                ", wordEct=" + wordEct +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}


