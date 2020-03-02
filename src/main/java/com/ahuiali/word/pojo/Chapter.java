package com.ahuiali.word.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Chapter implements Serializable {

    private Integer id;

    private String chapter_name;

    private Integer book_index;

    private Integer chapter_index;

    private Date created;

    private Date modified;

    private List<Paragraph> paragraphs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public Integer getBook_index() {
        return book_index;
    }

    public void setBook_index(Integer book_index) {
        this.book_index = book_index;
    }

    public Integer getChapter_index() {
        return chapter_index;
    }

    public void setChapter_index(Integer chapter_index) {
        this.chapter_index = chapter_index;
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

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", chapter_name='" + chapter_name + '\'' +
                ", book_index=" + book_index +
                ", chapter_index=" + chapter_index +
                ", created=" + created +
                ", modified=" + modified +
                ", paragraphs=" + paragraphs +
                '}';
    }
}
