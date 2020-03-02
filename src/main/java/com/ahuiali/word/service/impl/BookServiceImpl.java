package com.ahuiali.word.service.impl;

import com.ahuiali.word.json.BookJson;
import com.ahuiali.word.json.ChapterJson;
import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.ParagraphJson;
import com.ahuiali.word.mapper.BookMapper;
import com.ahuiali.word.pojo.Book;
import com.ahuiali.word.pojo.Chapter;
import com.ahuiali.word.pojo.Paragraph;
import com.ahuiali.word.service.BookService;
import com.ahuiali.word.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookJson bookJson;

    @Autowired
    JsonBase jsonBase;

    @Autowired
    ParagraphJson paragraphJson;

    @Autowired
    ChapterJson chapterJson;

    @Autowired
    StringRedisTemplate template;

    /**
     * 查询热门书籍
     * @return
     */
    @Override
    public BookJson findHotBooks() {
        List<Book> books = bookMapper.getHotBooks();
        if(books.size() > 0){
            bookJson.setBooks(books);
            bookJson.create(200,"success");
        }else {
            bookJson.create(805,"热门书籍为空");
        }
        return bookJson;
    }

    /**
     * 分类查询书籍
     * @param tag
     * @param pageUtil
     * @return
     */
    @Override
    public BookJson getBooksByTag(String tag, PageUtil pageUtil) {
        bookJson = new BookJson();
        List<Book> books = bookMapper.getBooksByTag(tag,pageUtil);
        if(books.size() > 0){
            bookJson.setBooks(books);
            bookJson.create(200,"success");
        }else{
            bookJson.create(800,"找不到书籍");
        }

        return bookJson;
    }

    /**
     * 获取书籍详情
     * @param index_book 书籍号
     * @param learner_id 用户id
     * @return
     */
    @Override
    public BookJson getBookDetail(Integer index_book, Integer learner_id) {

        //要改！

        bookJson = new BookJson();

        Book book = bookMapper.findBookByIndex(index_book,learner_id);
        if(book == null){
            bookJson.create(800,"没有该书籍");
            return bookJson;
        }
        System.out.println(book);
        //查询该用户有没有将该本书加入书架
//        String  count = bookMapper.findIsAddThisBook(index_book,learner_id);
        //有则将is_add设置为1
//        if(count > 0)
//            bookJson.setIs_add(1);
        bookJson.setBook(book);
        bookJson.create(200,"success");

        return bookJson;
    }

    /**
     * 获取用户书架，不需要分页
     * @param learner_id
     * @return
     */
    @Override
    public BookJson getMyBooks(Integer learner_id) {

        bookJson = new BookJson();

        List<Book> books = bookMapper.getMyBooks(learner_id);
        if(books.size() > 0 ){
            bookJson.setBooks(books);
            bookJson.create(200,"success");
        }else{
            bookJson.create(801,"用户书架为空");
        }
        return bookJson;
    }

    /**
     * 根据章节号返回该章节内容
     * @param chapter_index
     * @return
     */
    @Override
    public ChapterJson findParasByChapterIndex(Integer chapter_index) {
        chapterJson = new ChapterJson();
        Chapter chapter = bookMapper.getParaByChapterIndex(chapter_index);
        if(chapter != null){
            chapterJson.setChapter(chapter);
            chapterJson.create(200,"success");
        } else{
            chapterJson.create(807,"章节内容为空");
        }
        return chapterJson;
    }

    /**
     * 根据书号获取其所有的章节名称
     * @param index_book
     * @param pageUtil
     * @return
     */
    @Override
    public ChapterJson getAllChapterByBookIndex(Integer index_book, PageUtil pageUtil) {
        ChapterJson chapterJson = new ChapterJson();
        List<Chapter> chapters = bookMapper.getAllChapterByBookIndex(index_book,pageUtil);
        if(chapters.size()>0){
            chapterJson.setChapters(chapters);
            chapterJson.create(200,"success");
        } else{
            chapterJson.create(806,"书籍章节列表为空");
        }

        return chapterJson;
    }

    /**
     * 加入书架
     * @param index_book 书号
     * @param learner_id 用户id
     * @return
     */
    @Override
    public JsonBase addBook(Integer index_book, Integer learner_id) {
        jsonBase = new JsonBase();
        String lastest_loc = index_book+"0001_0";
        Integer count = bookMapper.addBook(index_book,learner_id,lastest_loc);
        if(count > 0){
            jsonBase.create(200,"success");
        }else {
            jsonBase.create(802,"书籍加入书架失败");
        }
        return jsonBase;
    }

    /**
     * 从书架中删除书本
     * @param learner_id
     * @param index_book
     * @return
     */
    @Override
    public JsonBase removeBook(Integer learner_id, Integer index_book) {
        jsonBase = new JsonBase();

        Integer count = bookMapper.removeBook(learner_id,index_book);
        if(count > 0){
            jsonBase.create(200,"success");
        }else {
            jsonBase.create(803,"书籍移出书架失败");
        }
        return jsonBase;
    }

    /**
     * 更新最新阅读位置
     * @param learner_id
     * @param book_index
     * @param lastest_loc
     * @return
     */
    @Override
    public JsonBase updateBook(Integer learner_id, Integer book_index, String lastest_loc) {
        jsonBase = new JsonBase();

        Integer count = bookMapper.updateBook(learner_id,book_index,lastest_loc);
        if(count > 0){
            jsonBase.create(200,"success");
        }else {
            jsonBase.create(804,"更新书籍最新阅读位置失败");
        }
        return jsonBase;
    }



    @Override
    public ParagraphJson findParaCNById(Integer para_id) {
        paragraphJson = new ParagraphJson();

        Paragraph paragraph = bookMapper.findParaCNById(para_id);

        if(paragraph != null){
            paragraphJson.setParagraph(paragraph);
            paragraphJson.create(200,"success");
        } else{
            paragraphJson.create(808,"段落翻译为空");
        }

        return paragraphJson;
    }

    /**
     * 根据书名查询书籍
     * @param bookName
     * @return
     */
    @Override
    public BookJson getBooksByName(String bookName) {
        bookJson = new BookJson();

        List<Book> books = bookMapper.getBooksByName(bookName);
        if(books.size() > 0){
            bookJson.setBooks(books);
            bookJson.create(200,"success");
        }else {
            bookJson.create(800,"找不到书籍");
        }
        return bookJson;
    }
}
