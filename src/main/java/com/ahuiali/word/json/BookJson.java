package com.ahuiali.word.json;

import com.ahuiali.word.pojo.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class BookJson extends JsonBase {

    private Book book;

    private List<Book> books;

    private Integer is_add = 0;



    public Integer getIs_add() {
        return is_add;
    }

    public void setIs_add(Integer is_add) {
        this.is_add = is_add;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookJson{" +
                "book=" + book +
                ", books=" + books +
                ", is_add=" + is_add +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
