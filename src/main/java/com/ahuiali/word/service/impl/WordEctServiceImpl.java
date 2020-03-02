package com.ahuiali.word.service.impl;

import com.ahuiali.word.json.WordEctDetailJson;
import com.ahuiali.word.json.WordEctJson;
import com.ahuiali.word.mapper.NotebookMapper;
import com.ahuiali.word.mapper.WordEctMapper;
import com.ahuiali.word.pojo.Sentence;
import com.ahuiali.word.pojo.WordEct;
import com.ahuiali.word.pojo.WordEctDetail;
import com.ahuiali.word.service.WordEctService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class WordEctServiceImpl implements WordEctService {

    @Autowired
    WordEctJson wordEctJson;

    @Autowired
    WordEctMapper wordEctMapper;

    @Autowired
    WordEctDetailJson wordEctDetailJson;

    @Autowired
    StringRedisTemplate template;

    @Autowired
    NotebookMapper notebookMapper;

    @Autowired
    WordEctDetail wordEctDetail;

    @Autowired
    WordEct wordEct;

    /**
     * 通过单词前缀来模糊查询单词，自动提示效果
     * @param wordpre
     * @return
     */
    @Override
    public WordEctJson getWordsByPre(String wordpre) {
        wordEctJson = new WordEctJson();

        //数据库中查找
        List<WordEct> wordEctList = wordEctMapper.getWordsByPre(wordpre);

        //如果大于0说明仍有提示
        if(wordEctList.size() > 0){
            wordEctJson.setWordEctList(wordEctList);
            wordEctJson.create(200,"success");
        } else {
            wordEctJson.create(700,"该单词模糊查询已无结果");
        }
        return wordEctJson;
    }

    /**
     * 查找单词详细信息
     * @param word
     * @param learner_id
     * @return
     */
    @Override
    public WordEctDetailJson findWordDetail(String word, Integer learner_id) {
        wordEctDetailJson = new WordEctDetailJson();

        String wordRedis = template.opsForValue().get(word);
        //如果redis中能找到
        if(wordRedis != "" && wordRedis != null){
            //将json转换为单词对象
            wordEctDetail =  JSON.parseObject(wordRedis,WordEctDetail.class);
            System.out.println(wordEctDetail.toString());
        } else{
            //从数据库中查询
            wordEctDetail = wordEctMapper.findWordEctDetail(word,21);

            //数据库中仍找不到
            if(wordEctDetail == null){
                wordEctDetailJson.create(701,"数据库中找不到该单词");
                return wordEctDetailJson;
            }
        }


        //拆分例句id
        String senStr = wordEctDetail.getSentence_list();
        if(senStr != null && senStr != ""){
            List<String> sens = Arrays.asList(senStr.split(","));
            if(sens.size() > 0){
                //从redis中获取例句
                List<String > sentences = template.opsForValue().multiGet(sens);
                List<Sentence> sentenceList = new ArrayList<>();
                sentences.forEach(s -> {
                    //json转例句对象
                    sentenceList.add(JSON.parseObject(s, Sentence.class));
                });
                //设置例句
                wordEctDetail.setSentences(sentenceList);
                //清空例句id，减少数据*
                wordEctDetail.setSentence_list("");
            }
        }

        //查询该单词是否已收藏
        Integer notebook_id = notebookMapper.findWordExistNotebooks(wordEctDetail.getWord(),learner_id);
        //已收藏
        if(notebook_id != null && notebook_id > 0){
            wordEctDetail.setNotebook_id(notebook_id);
        }

        //将单词详细加入json
        wordEctDetailJson.setWordEctDetail(wordEctDetail);
        //成功，返回200
        wordEctDetailJson.create(200,"success");

        return wordEctDetailJson;
    }

    /**
     * 查询单词（释义音标）
     * @param word
     * @param learner_id
     * @return
     */
    @Override
    public WordEctJson findWord(String word, Integer learner_id) {

        wordEctJson = new WordEctJson();
        String wordRedis = template.opsForValue().get(word);
        //如果redis中能找到
        if(wordRedis != "" && wordRedis != null){
            //将json转换为单词对象
            wordEct =  JSON.parseObject(wordRedis,WordEct.class);

        } else{
            wordEct = wordEctMapper.findWord(word);

            if(wordEct == null){
                wordEctJson.create(701,"数据库中找不到该单词");
                return wordEctJson;
            }
        }
        //查询该单词是否已收藏
        Integer notebook_id = notebookMapper.findWordExistNotebooks(wordEctDetail.getWord(),learner_id);
        //已收藏
        if(notebook_id != null && notebook_id > 0){
            wordEct.setNotebook_id(notebook_id);
        }

        wordEctJson.setWordEct(wordEct);
        wordEctJson.create(200,"success");

        return wordEctJson;
    }
}
