package com.ahuiali.word.pojo;

import java.io.Serializable;
import java.util.List;

public class PronAndSentences implements Serializable {

    private String pron_uk;

    private String pron_us;

    private List<Sentence> sentences;

    public String getPron_uk() {
        return pron_uk;
    }

    public void setPron_uk(String pron_uk) {
        this.pron_uk = pron_uk;
    }

    public String getPron_us() {
        return pron_us;
    }

    public void setPron_us(String pron_us) {
        this.pron_us = pron_us;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String toString() {
        return "PronAndSentences{" +
                "pron_uk='" + pron_uk + '\'' +
                ", pron_us='" + pron_us + '\'' +
                ", sentences=" + sentences +
                '}';
    }
}
