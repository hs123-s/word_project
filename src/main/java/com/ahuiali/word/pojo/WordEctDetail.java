package com.ahuiali.word.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class WordEctDetail extends WordEct implements Serializable {

    private Integer id_d;

    private String definition;

    private Integer collins;

    private Integer oxford;

    private String tag;

    private Integer bnc;

    private Integer frq;

    private String exchange;

    private String sentence_list;

    private List<Sentence> sentences;

    //是否收藏
    private Integer notebook_id = 0;

    public Integer getNotebook_id() {
        return notebook_id;
    }

    public void setNotebook_id(Integer notebook_id) {
        this.notebook_id = notebook_id;
    }

    public Integer getId_d() {
        return id_d;
    }

    public void setId_d(Integer id_d) {
        this.id_d = id_d;
    }


    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Integer getCollins() {
        return collins;
    }

    public void setCollins(Integer collins) {
        this.collins = collins;
    }

    public Integer getOxford() {
        return oxford;
    }

    public void setOxford(Integer oxford) {
        this.oxford = oxford;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getBnc() {
        return bnc;
    }

    public void setBnc(Integer bnc) {
        this.bnc = bnc;
    }

    public Integer getFrq() {
        return frq;
    }

    public void setFrq(Integer frq) {
        this.frq = frq;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSentence_list() {
        return sentence_list;
    }

    public void setSentence_list(String sentence_list) {
        this.sentence_list = sentence_list;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String toString() {
        return "WordEctDetail{" +
                "id_d=" + id_d +
                ", definition='" + definition + '\'' +
                ", collins=" + collins +
                ", oxford=" + oxford +
                ", tag='" + tag + '\'' +
                ", bnc=" + bnc +
                ", frq=" + frq +
                ", exchange='" + exchange + '\'' +
                ", sentence_list='" + sentence_list + '\'' +
                ", sentences=" + sentences +
                ", notebook_id=" + notebook_id +
                super.toString()+
                '}';
    }
}
