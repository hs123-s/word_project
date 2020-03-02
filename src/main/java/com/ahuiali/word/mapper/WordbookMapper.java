package com.ahuiali.word.mapper;

import com.ahuiali.word.pojo.Word;
import com.ahuiali.word.pojo.Wordbook;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WordbookMapper {


    List<Wordbook> findAllWordbook();

    //返回词书的详细情况
    @Select(" SELECT id, NAME, summary, img ,COUNT,(SELECT COUNT(*) " +
            "FROM learner_wordbook " +
            "WHERE wordbook_id = #{id} AND learner_id = #{learner_id}\n" +
            ") AS is_memorizing FROM wordbook WHERE id = #{id} LIMIT 1;")
    Wordbook getWordbookDetailAndIsAdd(Integer id,Integer learner_id);

    //返回词书的详细情况
    @Select("select id, name, summary,count,img from wordbook where id = #{id}")
    Wordbook getWordbookDetail(Integer id);

    //分页获取某词书的单词
    @Select("select id, word, paraphrase, pron_us, pron_uk from words where wordbook_id = #{id} limit #{curr},#{size};")
    List<Word> getWords(Integer id, int curr, int size);

    //移除当前计划
    @Update("update learner_wordbook set is_memorizing = 0, modified = NOW() where learner_id = #{learner_id} and is_memorizing = 1;")
    Integer removePlan(Integer learner_id);

    //为用户添加新词书
    @Insert("insert into learner_wordbook (learner_id,wordbook_id,learned_count,is_memorizing,created,modified)" +
            "values (#{learner_id},#{wordbook_id},0,1,NOW(),NOW());")
    @Options(useGeneratedKeys=true, keyProperty="id")
    Integer addWordbook(Integer learner_id, Integer wordbook_id);

    //查询用户的词书
    @Select("SELECT lw.`learned_count`,lw.`is_memorizing`,w.`count`,w.`id`,w.`name`,w.`img` \n" +
            "FROM learner_wordbook lw \n" +
            "INNER JOIN wordbook w \n" +
            "ON lw.`wordbook_id` = w.`id` \n" +
            "AND lw.`learner_id` = #{learner_id};")
    List<Wordbook> findMyWordbooks(Integer learner_id);

    //查询当前计划
    @Select("SELECT lw.`learned_count`,w.`count`,w.`id`,w.`name`,w.`img` \n" +
            "FROM learner_wordbook lw \n" +
            "INNER JOIN wordbook w \n" +
            "ON lw.`wordbook_id` = w.`id` \n" +
            "AND lw.`learner_id` = #{learner_id} " +
            "AND lw.is_memorizing = 1;")
    Wordbook findMemorizingWordbook(Integer learner_id);

    //查询当前计划的同时返回需复习单词数量
    @Select("SELECT lw.`learned_count`,w.`count`,w.`id`,w.`name`,\n" +
            "\t(SELECT COUNT(*) FROM memorize \n" +
            "\t\t  WHERE learner_id = #{learner_id}\n" +
            "\t\t  AND wordbook_id = w.`id`\n" +
            "\t\t  AND is_get = 0 \n" +
            "\t\t  AND NOW() > next_time) AS review_count\n" +
            "        FROM learner_wordbook lw \n" +
            "        INNER JOIN wordbook w\n" +
            "        ON lw.`wordbook_id` = w.`id` \n" +
            "        AND lw.`learner_id` = #{learner_id}\n" +
            "        AND lw.is_memorizing = 1;")
    Wordbook getMemorizingWordbookAndReviewCount(Integer learner_id);

    //设置新计划
    @Update("update learner_wordbook set is_memorizing = 1, modified = NOW() " +
            "where learner_id = #{learner_id} and wordbook_id = #{wordbook_id}")
    Integer updateWordbookPlan(Integer learner_id, Integer wordbook_id);
}
