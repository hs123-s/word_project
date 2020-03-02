package com.ahuiali.word.pojo;

import java.io.Serializable;

/**
 * Created by shkstart on 2019/10/6
 */
public class Sentence implements Serializable {

    private Integer id;

    private String en;

    private String cn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + id +
                ", sentence_en='" + en + '\'' +
                ", sentence_cn='" + cn + '\'' +
                '}';
    }
}
