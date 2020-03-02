package com.ahuiali.word.mapper;


import com.ahuiali.word.pojo.Sentence;
import com.ahuiali.word.pojo.WordEct;
import com.ahuiali.word.pojo.WordEctDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shkstart on 2019/10/6
 */
@Repository
@Mapper
public interface SentencesMapper {

    @Select("select id, sentence_en as en, sentence_cn as cn from sentence")
    List<Sentence> findAllSentences();

    @InsertProvider(type = Provider.class, method = "insertSens")
    void insertSens(String s);

    @Select("select * from sentence where id in (#{list})")
    List<Sentence> getSentenceByList(String list);

    @Select("select id from sentence where sentence_en like concat('',#{param1},' %') " +
            "or sentence_en like concat('% ',#{param2},' %')" +
            "or sentence_en like concat('% ',#{param3},'') limit 20")
    List<Integer> findAllIdByLike(String word1, String word2, String word3);

    @Select("select word, sentence_list from word_sentences ")
    List<WordEctDetail> getWordSentences();


    @Insert("insert into word_sentence (word_id,word,sentence_list) value (#{id},#{word},#{idlist})")
    void addWordAndSentence(Integer id, String word , String idlist);


    class Provider{
        public String insertSens(String s){

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("insert into sentence(sentence_en,sentence_cn) values ");
            stringBuilder.append(s);
            return stringBuilder.toString();
        }
    }
}
