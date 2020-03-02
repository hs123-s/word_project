package com.ahuiali.word.redis;

import com.ahuiali.word.mapper.NotebookMapper;
import com.ahuiali.word.mapper.SentencesMapper;
import com.ahuiali.word.mapper.WordEctMapper;
import com.ahuiali.word.pojo.Sentence;
import com.ahuiali.word.pojo.WordEctDetail;
import com.ahuiali.word.service.WordEctService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisInit {

    @Autowired
    StringRedisTemplate template;

    @Autowired
    SentencesMapper sentencesMapper;

    @Autowired
    WordEctMapper wordEctMapper;

    @Autowired
    NotebookMapper notebookMapper;

    @Autowired
    WordEctService wordEctService;


    //把word_sentence加入redis
    @Test
    public void initWord_sentence(){
        Map<String,String> map = new HashMap<String,String>();
        List<WordEctDetail> wordEcts = sentencesMapper.getWordSentences();
        wordEcts.forEach(wordEct -> {
            map.put("list",wordEct.getSentence_list());
            template.opsForHash().putAll(wordEct.getWord(),map);
            map.clear();
        });
    }

    //把所有句子加载到内存
    @Test
    public void initSentence() {
        List<Sentence> sentences = sentencesMapper.findAllSentences();
        sentences.forEach(sentence -> {
            String key = sentence.getId() + "";
            String value = JSON.toJSONString(sentence);
            template.opsForValue().set(key, value);
        });
    }



}