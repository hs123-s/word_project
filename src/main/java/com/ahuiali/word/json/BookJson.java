package com.ahuiali.word.json;

import com.ahuiali.word.pojo.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用于存放book的json
 */
@Component
@Scope("prototype")
public class BookJson extends JsonBase {

    //单个book
    private Book book;

    //多个books
    private List<Book> books;

    //是否已加入书架，这里只有在书籍详情才能看到是否加入书架，在书籍列表是看不到的
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
