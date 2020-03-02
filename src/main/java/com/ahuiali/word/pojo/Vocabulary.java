package com.ahuiali.word.pojo;

import java.util.Date;

/**
 * Created by shkstart on 2019/9/28
 */
public class Vocabulary {

    private Integer id;

    private Integer wordbook_id;

    private Integer wordRank;

    private String word;

    private String paraphrase;

    private String pron_us;

    private String pron_uk;

    private Date created;

    private Date modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWordbook_id() {
        return wordbook_id;
    }

    public void setWordbook_id(Integer wordbook_id) {
        this.wordbook_id = wordbook_id;
    }

    public Integer getWordRank() {
        return wordRank;
    }

    public void setWordRank(Integer wordRank) {
        this.wordRank = wordRank;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "id=" + id +
                ", wordbook_id=" + wordbook_id +
                ", wordRank=" + wordRank +
                ", word='" + word + '\'' +
                ", paraphrase='" + paraphrase + '\'' +
                ", pron_us='" + pron_us + '\'' +
                ", pron_uk='" + pron_uk + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }


}
