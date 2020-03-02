package com.ahuiali.word.mapper;

import com.ahuiali.word.pojo.Book;
import com.ahuiali.word.pojo.Chapter;
import com.ahuiali.word.pojo.Paragraph;
import com.ahuiali.word.utils.PageUtil;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {

    @Select("SELECT id,title,img,author,tag,summary,index_book,is_hot FROM book;")
    List<Book> getAllBooks();

    //根据书籍号查询所有章节
    @Select("SELECT id,chapter_name,chapter_index FROM book_chapter " +
            "WHERE book_index = #{book_index} " +
            "limit #{pageUtil.offset},#{pageUtil.size};")
    List<Chapter> getAllChapterByBookIndex(Integer book_index, PageUtil pageUtil);

    @Select("select id,title,index_book, img from book where is_hot = 1;")
    List<Book> getHotBooks();

    @Select("select id,title,index_book,img from book where tag like #{tag} " +
            "LIMIT #{pageUtil.offset},#{pageUtil.size};")
    List<Book> getBooksByTag(String tag, PageUtil pageUtil);

    @Select("select lastest_loc from learner_book where learner_id = #{learner_id} and book_index = #{book_index}")
    String findIsAddThisBook(Integer book_index, Integer learner_id);

    //根据书籍号查询，并且返回是否加入
    @Select("SELECT id,title,index_book,img,tag,summary, " +
            "(SELECT lastest_loc FROM learner_book WHERE learner_id = #{learner_id} AND book_index = #{index_book} limit 1) as lastest_loc \n" +
            "FROM book WHERE index_book = #{index_book} limit 1;")
    Book findBookByIndex(Integer index_book,Integer learner_id);

    //查询我的书籍,结果按照时间排序，最新排在最前
    @Select("SELECT lb.`lastest_loc`,b.`title`,b.`tag`,b.`title`,b.`author`,b.`img`,b.`index_book`\n" +
            "FROM learner_book lb \n" +
            "INNER JOIN book b \n" +
            "ON (lb.`learner_id` = #{learner_id} AND lb.`book_index` = b.`index_book`) ORDER BY lb.`modified` DESC;")
    List<Book> getMyBooks(Integer learner_id);

//    //根据章节号返回所有段落
//    @Select("SELECT id,para_en,para_cn FROM chapter_paragraph WHERE chapter_index = #{chapter_index};")
//    List<Paragraph> getParaByChapterIndex(Integer chapter_index);

    //根据章节号返回所有段落
    @Select("SELECT id, chapter_name, chapter_index FROM book_chapter WHERE chapter_index = #{chapter_index};")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="chapter_name",column="chapter_name"),
            @Result(property="chapter_index",column="chapter_index"),
            @Result(property="paragraphs",column="chapter_index",javaType=List.class,
                    many=@Many(select="com.ahuiali.word.mapper.BookMapper.getAllParasByChapterIndex"))
    })
    Chapter getParaByChapterIndex(Integer chapter_index);

    //查询某章节的所有英语段落
    @Select("select id,para_en from chapter_paragraph where chapter_index = #{chapter_index};")
    List<Paragraph> getAllParasByChapterIndex(Integer chapter_index);

    @Insert("insert into learner_book (learner_id,book_index,lastest_loc,created,modified) \n" +
            "values (#{learner_id},#{index_book},#{lastest_loc},NOW(),NOW());")
    Integer addBook(Integer index_book, Integer learner_id,String lastest_loc);

    @Delete("delete from learner_book where learner_id = #{learner_id} and book_index = #{book_index};")
    Integer removeBook(Integer learner_id, Integer book_index);

    @Update("update learner_book set lastest_loc = #{lastest_loc},modified = NOW() " +
            "where learner_id = #{learner_id} and book_index = #{book_index};")
    Integer updateBook(Integer learner_id, Integer book_index, String lastest_loc);

    //根据段落id查询段落翻译
    @Select("SELECT para_cn FROM chapter_paragraph WHERE id = #{para_id};")
    Paragraph findParaCNById(Integer para_id);

    /**
     * 根据书名查询书籍
     * @param bookName
     * @return
     */
    @Select("select id,title,index_book, img from book where title like concat('%',#{bookName},'%');")
    List<Book> getBooksByName(String bookName);
}
