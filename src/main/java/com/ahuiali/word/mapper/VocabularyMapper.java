package com.ahuiali.word.mapper;


import com.ahuiali.word.pojo.Vocabulary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shkstart on 2019/9/28
 */

@Repository
@Mapper
public interface VocabularyMapper {

    @Select("select * from words WHERE pron_us = \"null\" or pron_uk = \"null\"")
    List<Vocabulary> findAllVocabularyMeanIsNull();

    @Update("update words set pron_us = #{pron_us},pron_uk = #{pron_uk} where id = #{id}")
    void update(Vocabulary vocabulary);

}
