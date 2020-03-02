package com.ahuiali.word.service.impl;

import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.WordbookJson;
import com.ahuiali.word.mapper.WordMapper;
import com.ahuiali.word.mapper.WordbookMapper;
import com.ahuiali.word.pojo.Word;
import com.ahuiali.word.pojo.Wordbook;
import com.ahuiali.word.service.WordbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
//@EnableTransactionManagement
public class WordbookServiceImpl implements WordbookService {

    @Autowired
    WordbookMapper wordbookMapper;

    @Autowired
    WordMapper wordMapper;

    @Autowired
    WordbookJson wordbookJson;

    @Autowired
    JsonBase jsonBase;

    /**
     * 查询所有词书
     * @return
     */
    @Override
    public WordbookJson getWordbooks() {
        wordbookJson = new WordbookJson();

        List<Wordbook> wordbooks = wordbookMapper.findAllWordbook();

        if(wordbooks != null){
            wordbookJson.setCode(200);
            wordbookJson.setMessage("success");
            wordbookJson.setWordbooks(wordbooks);
            return wordbookJson;
        } else {
            wordbookJson.setCode(500);
            wordbookJson.setMessage("找不到词书");
            return wordbookJson;
        }
    }

    /**
     * 获取词书细节
     * @param id
     * @param learner_id
     * @return
     */
    @Override
    public WordbookJson getWordbookDetail(Integer id, Integer learner_id) {
        wordbookJson = new WordbookJson();

        Wordbook wordbook = wordbookMapper.getWordbookDetailAndIsAdd(id,learner_id);

        if(wordbook != null){

            wordbookJson.create(200, "success",wordbook);
            return wordbookJson;
        } else {
            wordbookJson.create(500, "找不到词书");
            return wordbookJson;
        }

    }

    /**
     *  获取词书单词
     * @param id
     * @param curr
     * @param size
     * @return
     */
    @Override
    public WordbookJson getWords(Integer id, int curr, int size) {
        wordbookJson = new WordbookJson();

        List<Word> words = wordbookMapper.getWords(id,(curr-1)*size,size);
        Wordbook wordbook = new Wordbook();
        wordbook.setWords(words);

        return wordbookJson;
    }

    /**
     * 为用户添加词书
     * 这里要开启事务，但是还不太了解事务，所以以后再完善
     * @param learnerId
     * @param wordbook_id
     * @return
     */
//    @Transactional(rollbackFor=Exception.class)
    @Override
    public JsonBase addWordbook(Integer learnerId, Integer wordbook_id){
        jsonBase = new JsonBase();
        //将原先计划去掉
        wordbookMapper.removePlan(learnerId);
        //新增计划，total为影响条数
        Integer total = wordbookMapper.addWordbook(learnerId,wordbook_id);
        if(total > 0)
            jsonBase.create(200,"success");
        else
            jsonBase.create(501,"用户添加词书失败");

        return jsonBase;
    }

    /**
     * 查询我的词书
     * @param learnerId
     * @return
     */
    @Override
    public WordbookJson findMyWordbooks(Integer learnerId) {
        wordbookJson = new WordbookJson();

        //查询我的词书
        List<Wordbook> myWordbooks = wordbookMapper.findMyWordbooks(learnerId);

        //如果没有词书，则返回502
        if(myWordbooks.size() == 0){
            wordbookJson.create(502,"用户无词书");
        }else {
            wordbookJson.create(200,"success",myWordbooks);
        }
        return wordbookJson;
    }

    /**
     * 更新背词计划
     * @param learnerId
     * @param wordbook_id
     * @return
     */
    @Override
    public JsonBase updateWordbookPlan(Integer learnerId, Integer wordbook_id) {
        jsonBase = new JsonBase();

        //将原先计划去掉
        wordbookMapper.removePlan(learnerId);
        //修改计划
        Integer total = wordbookMapper.updateWordbookPlan(learnerId,wordbook_id);
        if(total == 1)
            jsonBase.create(200,"success");
        else
            jsonBase.create(503,"用户设置词书计划失败");

        return jsonBase;
    }

    /**
     *查看用户当前的计划
     * @param learnerId
     * @return
     */
    @Override
    public WordbookJson myMemorizingWordbook(Integer learnerId) {
        wordbookJson = new WordbookJson();
        Wordbook wordbook = wordbookMapper.findMemorizingWordbook(learnerId);
        if(wordbook != null){
            wordbookJson.create(200,"success",wordbook);
        }else {
            wordbookJson.create(500,"找不到词书");
        }
        return wordbookJson;
    }



    /**
     * 查询复习单词数目
     * @param learnerId
     * @param wordbook_id
     * @return
     */
    @Override
    public Integer findReviewCount(Integer learnerId, Integer wordbook_id) {
        return wordMapper.getReviewCount(learnerId,wordbook_id);
    }

    /**
     *查看用户当前的计划并返回复习单词数量
     * @param learnerId
     * @return
     */
    @Override
    public WordbookJson getMemorizingWordbookAndReviewCount(Integer learnerId) {
        wordbookJson = new WordbookJson();
        Wordbook wordbook = wordbookMapper.getMemorizingWordbookAndReviewCount(learnerId);
        if(wordbook != null){
            wordbookJson.create(200,"success",wordbook);
        }else {
            wordbookJson.create(500,"找不到词书");
        }
        return wordbookJson;
    }
}
