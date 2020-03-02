package com.ahuiali.word.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Wordbook implements Serializable {

    //词书id
    private Integer id;

    //词书名
    private String name;

    //简要
    private String summary;

    //词书单词数目
    private Integer count;

    //词书已背单词数目
    private Integer learned_count;

    //词书img
    private String img;

    //创建日期
    private Date created;

    //修改日期
    private Date modified;

    //我的词书里面的计划(从词库中查询详细时，该字段作为是否已添加)
    private Integer is_memorizing;

    //需复习单词数
    private Integer review_count;

    //词书词表单词
    private List<Word> words;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setLearned_count(Integer learned_count) {
        this.learned_count = learned_count;
    }

    public Integer getLearned_count() {
        return learned_count;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
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

    public void setIs_memorizing(Integer is_memorizing) {
        this.is_memorizing = is_memorizing;
    }

    public Integer getIs_memorizing() {
        return is_memorizing;
    }

    public Integer getReview_count() {
        return review_count;
    }

    public void setReview_count(Integer review_count) {
        this.review_count = review_count;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Wordbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", count=" + count +
                ", img=" + img +
                ", learned_count=" + learned_count +
                ", created=" + created +
                ", modified=" + modified +
                ", is_memorizing=" + is_memorizing +
                ", review_count=" + review_count +
                ", words=" + words +
                '}';
    }
}
