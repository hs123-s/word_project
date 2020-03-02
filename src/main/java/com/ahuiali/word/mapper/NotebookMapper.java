package com.ahuiali.word.mapper;


import com.ahuiali.word.pojo.Notebook;
import com.ahuiali.word.pojo.Word;
import com.ahuiali.word.utils.PageUtil;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotebookMapper {
    //查询用户的所有生词本
    @Select("select id,name,count from notebook where learner_id = #{leaner_id} order by modified desc")
    List<Notebook> findAllNotebookByLearnerId(Integer learner_id);

    //新建生词本
    @Insert("insert into notebook (learner_id,name,count,created,modified)" +
            " values (#{learner_id},#{name},0,NOW(),NOW())")
    Integer addNotebook(Notebook notebook);

    //删除生词本
    @Delete("delete table notebook where id = #{id}")
    Integer removeNotebook(Integer id);

    //从生词本中移除单词
    @Delete("delete table notebook_word where id = #{id}")
    Integer removeWord(Integer id);

    //添加单词到生词本
    @Insert("INSERT INTO notebook_word (notebook_id,word,pron_us,pron_uk,paraphrase) " +
            "SELECT 1,word,pron_us,pron_uk,paraphrase " +
            "FROM wordect " +
            "WHERE word = #{word};")
    Integer addWord(Integer notebook_id, String word);

    //分页显示生词本单词
    @Select("select id,word,pron_uk,pron_us,paraphrase from notebook_word where notebook_id = #{notebook_id} " +
            "limit #{pageUtil.offset},#{pageUtil.size};")
    List<Word> listWords(Integer notebook_id, PageUtil pageUtil);

    //查看该单词是否以及在该用户的生词表中
    @Select("SELECT id FROM notebook_word WHERE word = #{word} " +
            "AND notebook_id IN (SELECT id FROM notebook WHERE learner_id = #{learner_id}) limit 1;")
    Integer findWordExistNotebooks(String word, Integer learner_id);

    //修改名称
    @Update("UPDATE notebook SET NAME = #{name} , modified = NOW() WHERE id = #{id};")
    Integer editNotebookName(String name, Integer id);
}
