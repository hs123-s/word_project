package com.ahuiali.word.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 生词本
 */
public class Notebook implements Serializable {

    //id
    private Integer id;

    //用户id
    private Integer learner_id;

    //生词本名字
    private String name;

    //生词本单词数量
    private Integer count;

    //创建日期
    private Date created;

    //修改日期
    private Date modified;

    //生词本单词
    private List<Word> words;

    @Override
    public String toString() {
        return "notebook{" +
                "id=" + id +
                ", learner_id=" + learner_id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", created=" + created +
                ", modified=" + modified +
                ", words=" + words +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLearner_id() {
        return learner_id;
    }

    public void setLearner_id(Integer learner_id) {
        this.learner_id = learner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
