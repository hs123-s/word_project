package com.ahuiali.word.pojo;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;
import java.util.Date;

public class LearnerWordbook implements Serializable {

    private Integer id;

    private Integer learner_id;

    private Integer wordbook_id;

    private Integer learned_count;

    private Integer is_memorizing;

    private Date created;

    private Date modified;

    private Wordbook wordbook;

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

    public Integer getWordbook_id() {
        return wordbook_id;
    }

    public void setWordbook_id(Integer wordbook_id) {
        this.wordbook_id = wordbook_id;
    }

    public Integer getLearned_count() {
        return learned_count;
    }

    public void setLearned_count(Integer learned_count) {
        this.learned_count = learned_count;
    }

    public Integer getIs_memorizing() {
        return is_memorizing;
    }

    public void setIs_memorizing(Integer is_memorizing) {
        this.is_memorizing = is_memorizing;
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

    public Wordbook getWordbook() {
        return wordbook;
    }

    public void setWordbook(Wordbook wordbook) {
        this.wordbook = wordbook;
    }

    @Override
    public String toString() {
        return "LearnerWordbook{" +
                "id=" + id +
                ", learner_id=" + learner_id +
                ", wordbook_id=" + wordbook_id +
                ", learned_count=" + learned_count +
                ", is_memorizing=" + is_memorizing +
                ", created=" + created +
                ", modified=" + modified +
                ", wordbook=" + wordbook +
                '}';
    }
}
