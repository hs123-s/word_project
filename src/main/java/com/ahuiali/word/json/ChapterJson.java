package com.ahuiali.word.json;

import com.ahuiali.word.pojo.Chapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChapterJson extends JsonBase{

    List<Chapter> chapters;

    Chapter chapter;

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "ChapterJson{" +
                "chapters=" + chapters +
                ", chapter=" + chapter +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
