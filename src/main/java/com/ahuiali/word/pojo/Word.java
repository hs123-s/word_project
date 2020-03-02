package com.ahuiali.word.pojo;

/**
 * Created by shkstart on 2019/9/18
 */

import java.io.Serializable;

/**
 * 词书单词表，不是词表！
 */
public class Word implements Serializable {
    private int id;

    private int wordbook_id;

    private String word;

    private String paraphrase;

    private String pron_us;

    private String pron_uk;

    private Integer memorized_count;

    public void setMemorizing_count(Integer memorized_count) {
        this.memorized_count = memorized_count;
    }

    public Integer getMemorized_count() {
        return memorized_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWordbook_id() {
        return wordbook_id;
    }

    public void setWordbook_id(int wordbook_id) {
        this.wordbook_id = wordbook_id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getParaphrase() {
        return paraphrase;
    }

    public void setParaphrase(String paraphrase) {
        this.paraphrase = paraphrase;
    }

    public String getPron_us() {
        return pron_us;
    }

    public void setPron_us(String pron_us) {
        this.pron_us = pron_us;
    }

    public String getPron_uk() {
        return pron_uk;
    }

    public void setPron_uk(String pron_uk) {
        this.pron_uk = pron_uk;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", wordbook_id=" + wordbook_id +
                ", word='" + word + '\'' +
                ", paraphrase='" + paraphrase + '\'' +
                ", pron_us='" + pron_us + '\'' +
                ", pron_uk='" + pron_uk + '\'' +
                ", memorized_count=" + memorized_count +
                '}';
    }
}
