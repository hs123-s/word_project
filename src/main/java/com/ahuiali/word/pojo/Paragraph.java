package com.ahuiali.word.pojo;

import java.io.Serializable;
import java.util.Date;

public class Paragraph implements Serializable {

    private Integer id;

    private String para_en;

    private String para_cn;

    private Integer chapter_index;

    private Integer para_index;

    private Date created;

    private Date modified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPara_en() {
        return para_en;
    }

    public void setPara_en(String para_en) {
        this.para_en = para_en;
    }

    public String getPara_cn() {
        return para_cn;
    }

    public void setPara_cn(String para_cn) {
        this.para_cn = para_cn;
    }

    public Integer getChapter_index() {
        return chapter_index;
    }

    public void setChapter_index(Integer chapter_index) {
        this.chapter_index = chapter_index;
    }

    public Integer getPara_index() {
        return para_index;
    }

    public void setPara_index(Integer para_index) {
        this.para_index = para_index;
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
        return "Paragraph{" +
                "id=" + id +
                ", para_en='" + para_en + '\'' +
                ", para_cn='" + para_cn + '\'' +
                ", chapter_index=" + chapter_index +
                ", para_index=" + para_index +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
