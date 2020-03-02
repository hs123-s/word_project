package com.ahuiali.word.pojo;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

    private Integer id;

    private String title;

    private String img;

    private String author;

    private String tag;

    //是否推荐，0否1是
    private Integer is_hot;

    private String summary;

    private Integer index_book;

    private String lastest_loc;

    private List<Chapter> chapters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public Integer getIndex_book() {
        return index_book;
    }

    public void setIndex_book(Integer index_book) {
        this.index_book = index_book;
    }

    public void setLastest_loc(String lastest_loc) {
        this.lastest_loc = lastest_loc;
    }

    public String getLastest_loc() {
        return lastest_loc;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", author='" + author + '\'' +
                ", tag='" + tag + '\'' +
                ", is_hot=" + is_hot +
                ", summary='" + summary + '\'' +
                ", index_book=" + index_book +
                ", lastest_loc='" + lastest_loc + '\'' +
                ", chapters=" + chapters +
                '}';
    }
}
