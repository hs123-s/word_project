package com.ahuiali.word.controller;

import com.ahuiali.word.json.BookJson;
import com.ahuiali.word.json.ChapterJson;
import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.ParagraphJson;
import com.ahuiali.word.service.BookService;
import com.ahuiali.word.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookJson bookJson;

    @Autowired
    JsonBase jsonBase;

    @Autowired
    ChapterJson chapterJson;

    @Autowired
    ParagraphJson paragraphJson;

    /**
     * 跳转至书城
     * @return
     */
    @RequestMapping(value = "/gotoBookStore")
    public String gotoBookStore(){
        return "/book/bookstore";
    }

    /**
     * 跳转至书籍详情
     * @return
     */
    @RequestMapping(value = "/gotoBookDetail")
    public String gotoBookDetail(){
        return "/book/bookDetail";
    }

    /**
     * 跳转至阅读界面
     * @return
     */
    @RequestMapping(value = "/gotoRead")
    public String gotoRead(){
        return "/book/read";
    }

    /**
 * 跳转至书架
 * @return
 */
    @RequestMapping(value = "/gotoShelf")
    public String gotoShelf(){
        return "/book/bookshelf";
    }

    /**
     * 初始化，自动显示热门书籍,不需要分页
     * @param
     * @return
     */
    @RequestMapping(value = "/hotbooks",produces = "application/json;charset=utf-8;")
    public @ResponseBody BookJson hotbooks(){
        bookJson = bookService.findHotBooks();
        return bookJson;
    }

    /**
     * 根据段落id获取释义
     * @param para_id
     * @return
     */
    @RequestMapping(value = "/getCn/{para_id}",produces = "application/json;charset=utf-8;")
    public @ResponseBody ParagraphJson getCn(@PathVariable("para_id") Integer para_id){
        paragraphJson = bookService.findParaCNById(para_id);
        return paragraphJson;
    }

    /**
     * 根据分类查询书籍
     * @param tag
     * @param
     * @return
     */
    @RequestMapping(value = "/tagbooks/{tag}",produces = "application/json;charset=utf-8;")
    public @ResponseBody BookJson getBooksByTag(@PathVariable("tag") String tag, @RequestBody PageUtil pageUtil){
        tag = tag.replaceAll("0","%");

        pageUtil.renew();

        bookJson = bookService.getBooksByTag(tag,pageUtil);
        return bookJson;
    }

    /**
     * 根据名字查询书籍
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/getBookByName/{bookName}",produces = "application/json;charset=utf-8;")
    public @ResponseBody BookJson getBooksByName(@PathVariable("bookName") String bookName){
        bookJson = bookService.getBooksByName(bookName);
        return bookJson;
    }

    /**
     * 查询书籍详情
     * @param index_book
     * @return
     */
    @RequestMapping(value = "/bookDetail/{index_book}",produces = "application/json;charset=utf-8;")
    public @ResponseBody BookJson getBookDetail(@PathVariable("index_book") Integer index_book, HttpSession session){

        Integer learner_id = (Integer) session.getAttribute("learnerId");
        bookJson = bookService.getBookDetail(index_book,learner_id);
        return bookJson;
    }

    /**
     * 书架，不需要分页，到时品字展示
     * @param session
     * @return
     */
    @RequestMapping(value = "/mybook",produces = "application/json;charset=utf-8;")
    public @ResponseBody BookJson getMyBooks(HttpSession session){

        Integer learner_id = (Integer) session.getAttribute("learnerId");
        bookJson = bookService.getMyBooks(learner_id);
        return bookJson;
    }


    /**
     * 根据书籍号查询所有章节
     * @param index_book
     * @param pageUtil
     * @return
     */
    @RequestMapping(value = "/mybook/chapters/{index_book}")
    public @ResponseBody ChapterJson listChapters(@PathVariable("index_book") Integer index_book,
                                                  PageUtil pageUtil){
        chapterJson = new ChapterJson();
        pageUtil.renew();
        chapterJson = bookService.getAllChapterByBookIndex(index_book,pageUtil);
        return chapterJson;
    }

    /**
     * 用户浏览文章(只有英)
     * @param
     *
     * @return
     */
    @RequestMapping(value = "/read/{chapter_index}")
    public @ResponseBody ChapterJson read(@PathVariable("chapter_index") Integer chapter_index){
        chapterJson = new ChapterJson();

        chapterJson  = bookService.findParasByChapterIndex(chapter_index);



//        Integer index_book = chapter_index/10000;
        //获取文章内容


        return chapterJson;
    }


    /**
     * 加入书架
     * @param index_book
     * @param session
     * @return
     */
    @RequestMapping(value = "/addBook/{index_book}",produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase addBook(@PathVariable("index_book") Integer index_book,
                                          HttpSession session){

        Integer learner_id = (Integer) session.getAttribute("learnerId");

        jsonBase = bookService.addBook(index_book,learner_id);

        return jsonBase;
    }

    /**
     * 从书架中删除书本
     * @param index_book
     * @param session
     * @return
     */
    @RequestMapping(value = "/remove/{index_book}",produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase removeBook(@PathVariable("index_book") Integer index_book,
                                         HttpSession session){
        Integer learner_id = (Integer) session.getAttribute("learnerId");
        jsonBase = bookService.removeBook(learner_id,index_book);
        return jsonBase;
    }

    /**
     * 更新最新阅读位置
     * @param book_index
     * @param lastest_loc
     * @param session
     * @return
     */
    @RequestMapping(value = "/update/{book_index}/{lastest_loc}",produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase updateBook(@PathVariable("book_index") Integer book_index,
                                             @PathVariable("lastest_loc") String lastest_loc,
                                             HttpSession session){
        Integer learner_id = (Integer) session.getAttribute("learnerId");

        jsonBase = bookService.updateBook(learner_id,book_index,lastest_loc);


        return jsonBase;
    }
}
