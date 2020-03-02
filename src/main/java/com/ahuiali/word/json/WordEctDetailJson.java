package com.ahuiali.word.json;

import com.ahuiali.word.pojo.WordEct;
import com.ahuiali.word.pojo.WordEctDetail;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class WordEctDetailJson extends WordEctJson {

    private List<WordEctDetail> wordEctDetails;

    private WordEctDetail wordEctDetail;

    public List<WordEctDetail> getWordEctDetails() {
        return wordEctDetails;
    }

    public void setWordEctDetails(List<WordEctDetail> wordEctDetails) {
        this.wordEctDetails = wordEctDetails;
    }

    public WordEctDetail getWordEctDetail() {
        return wordEctDetail;
    }

    public void setWordEctDetail(WordEctDetail wordEctDetail) {
        this.wordEctDetail = wordEctDetail;
    }

    @Override
    public String toString() {
        return "WordEctDetailJson{" +
                "wordEctDetails=" + wordEctDetails +
                ", wordEctDetail=" + wordEctDetail +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
