package com.ahuiali.word.mapper;

import com.ahuiali.word.pojo.WordEct;
import com.ahuiali.word.pojo.WordEctDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WordEctMapper {

    @Update("update wordect set pron_uk = #{pron_uk}, pron_us = #{pron_us} where id = #{id};")
    void updatePron(String pron_uk,String pron_us,Integer id);

    @Select("select id, word from wordect where id >= 150000 and id < 200000 " +
            "and (pron_us IS NOT NULL AND pron_us != \"暂无音标\")\n" +
            "AND (collins IS NOT NULL OR oxford IS NOT NULL OR tag IS NOT NULL OR definition IS NOT NULL)" )
    List<WordEct> findSomeWords();

    @Select("select id, word from wordect where (id < #{i} and id >= #{id}) and word NOT REGEXP '[^a-zA-Z]' AND pron_us IS NULL ;")
    List<WordEct> findSomeWordsById(Integer id, int i);

    @Select("select id, word from wordect where id >= #{start} and id < #{end} " +
            "and (pron_us IS NOT NULL AND pron_us != \"暂无音标\")\n" +
            "AND (collins IS NOT NULL OR oxford IS NOT NULL OR tag IS NOT NULL OR definition IS NOT NULL)" )
    List<WordEct> findSomeWordsBySE(Integer start, Integer end);

    @Select("SELECT w.`id`,w.`word`,w.`pron_uk`,w.`pron_us`,w.`translation`,\n" +
            "wd.`definition`,wd.`collins`,wd.`exchange`,wd.`oxford`,wd.`bnc`,wd.tag,wd.`sentence_list` \n" +
            "FROM wordect_detail wd INNER JOIN  wordect w \n" +
            "ON wd.`word_id` = w.`id`  WHERE \n" +
            "wd.collins IS NOT NULL \n" +
            "OR wd.oxford IS NOT NULL \n" +
            "OR wd.tag IS NOT NULL\n" +
            "OR wd.`frq` > 0 \n" +
            "OR wd.bnc > 0 \n" +
            "AND w.`translation` IS NOT NULL")
    List<WordEctDetail> findAllWordToRedis();

    @Select("select * from wordect")
    List<WordEct> findAllWordEctToRedis();

    @Select("SELECT w.id,w.`word`,w.`pron_uk`,w.`pron_us`,w.`translation`,n.`notebook_id`,\n" +
            "\twd.`definition`,wd.`bnc`,wd.`collins`,wd.`exchange`,wd.`frq`,wd.`tag` as tag,wd.`sentence_list`\n" +
            "\tFROM wordect w INNER JOIN wordect_detail wd \n" +
            "\tON w.id = wd.`word_id` \n" +
            "\tLEFT JOIN notebook_word n\n" +
            "\tON n.`notebook_id` IN (SELECT id FROM notebook WHERE learner_id = #{learner_id}) " +
            "\tAND w.`word` = n.`word` \n" +
            "\tWHERE w.`word` = #{word} limit 1; ")
    WordEctDetail findWordEctDetail(String word, Integer learner_id);


    @Select("select word, translation from enwords where word like concat('',#{word},'%') limit 5;")
    List<WordEct> getWordsByPre(String word);

    @Select("select id, word, pron_uk, pron_us,translation from wordect where word = #{word}")
    WordEct findWord(String word);
}
