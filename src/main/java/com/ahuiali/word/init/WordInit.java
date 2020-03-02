package com.ahuiali.word.init;


import com.ahuiali.word.mapper.SentencesMapper;
import com.ahuiali.word.mapper.WordEctMapper;
import com.ahuiali.word.pojo.Sentence;
import com.ahuiali.word.pojo.WordEctDetail;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目启动初始方法
 */
@Component
public class WordInit implements CommandLineRunner {

    @Autowired
    StringRedisTemplate template;

    @Autowired
    SentencesMapper sentencesMapper;

    @Autowired
    WordEctMapper wordEctMapper;

    @Override
    public void run(String... args) throws Exception {
//        //常用词
//        List<WordEctDetail> wordEctDetails = wordEctMapper.findAllWordToRedis();
//        wordEctDetails.forEach(wordEctDetail -> {
//            String value = JSON.toJSONString(wordEctDetail);
//
//            template.opsForValue().set(wordEctDetail.getWord(),value);
//        });
//
        //把所有句子加载到内存
//        List<Sentence> sentences = sentencesMapper.findAllSentences();
//        sentences.forEach(sentence -> {
//            String key = sentence.getId() + "";
//            String value = JSON.toJSONString(sentence);
//            template.opsForValue().set(key, value);
//        });
    }
}
