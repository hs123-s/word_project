package com.ahuiali.word.json;

import com.ahuiali.word.pojo.Paragraph;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class ParagraphJson extends JsonBase {

    private List<Paragraph> paragraphs;

    private Paragraph paragraph;

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    @Override
    public String toString() {
        return "ParagraphJson{" +
                "paragraphs=" + paragraphs +
                ", paragraph=" + paragraph +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
