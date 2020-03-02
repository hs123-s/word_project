package com.ahuiali.word.json;

import com.ahuiali.word.pojo.Notebook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class NotebookJson extends JsonBase {

    private List<Notebook> notebooks;

    public List<Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(List<Notebook> notebooks) {
        this.notebooks = notebooks;
    }

    @Override
    public String toString() {
        return "NotebookJson{" +
                "notebooks=" + notebooks +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
