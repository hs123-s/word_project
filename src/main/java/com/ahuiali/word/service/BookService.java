package com.ahuiali.word.service;

import com.ahuiali.word.json.BookJson;
import com.ahuiali.word.json.ChapterJson;
import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.ParagraphJson;
import com.ahuiali.word.pojo.Paragraph;
import com.ahuiali.word.utils.PageUtil;

import java.util.List;

public interface BookService {
    BookJson findHotBooks();

    BookJson getBooksByTag(String tag, PageUtil pageUtil);

    BookJson getBookDetail(Integer book_index, Integer learner_id);

    BookJson getMyBooks(Integer learner_id);

    ChapterJson findParasByChapterIndex(Integer chapter_index);

    ChapterJson getAllChapterByBookIndex(Integer index_book, PageUtil pageUtil);

    JsonBase addBook(Integer index_book, Integer learner_id);

    JsonBase removeBook(Integer learner_id, Integer index_book);

    JsonBase updateBook(Integer learner_id, Integer book_index, String lastest_loc);

    ParagraphJson findParaCNById(Integer para_id);


    BookJson getBooksByName(String bookName);
}
