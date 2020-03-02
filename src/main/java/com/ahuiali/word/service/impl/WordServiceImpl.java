package com.ahuiali.word.service.impl;

import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.WordJson;
import com.ahuiali.word.mapper.WordMapper;
import com.ahuiali.word.pojo.Word;
import com.ahuiali.word.service.WordService;
import com.ahuiali.word.utils.NextTimeUtils;
import com.ahuiali.word.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Transactional
@Service
public class WordServiceImpl implements WordService {

    @Autowired
    WordMapper wordMapper;

    @Autowired
    WordJson wordJson;

    @Autowired
    JsonBase jsonBase;

    @Autowired
    NextTimeUtils nextTimeUtils;
    @Override
    public WordJson getWords(int id, PageUtil pageUtil) {

        wordJson = new WordJson();
        List<Word> words = wordMapper.getWords(id,pageUtil);
        if(words.size() > 0){
            wordJson.create(200,"success",words);
        }else {
            wordJson.create(504,"词书单词为空",null);
        }
        return wordJson;
    }

    //获取不同类型单词，未背，记忆中，已掌握
    @Override
    public WordJson myWordbookWords(Integer wordbook_id, Integer learnerId, PageUtil pageUtil, Integer wordsType) {
        wordJson = new WordJson();
        List<Word> words = new ArrayList<>();
        //返回单词类型，1为未背，2为记忆中，3为已掌握
        if(wordsType == 1){
            words = wordMapper.getMyWordbookWords(wordbook_id,learnerId,pageUtil);

        } else if(wordsType == 2){
            words = wordMapper.findMemorizingWords(wordbook_id,learnerId,pageUtil);


        } else if(wordsType == 3){
            words = wordMapper.findMemorizdWords(wordbook_id,learnerId,pageUtil);

        } else{
            wordJson.create(-1,"参数出错");
            return wordJson;
        }
        if(words.size() == 0){
            wordJson.create(504,"词书单词为空");
            return wordJson;
        }
        wordJson.setWords(words);
        wordJson.create(200,"success");
        return wordJson;
    }

    /**
     *  单词类型转移
     *
     * @param wordbook_id
     * @param id 未背->掌握时id为words的id，其余为记忆表的id
     * @param type 记忆中->掌握 : 1, 未背->掌握 : 2, 掌握->未背 : 3
     * @param learner_id 用户id
     * @return
     */
    @Override
    public JsonBase wordTypeChange(Integer learner_id, Integer wordbook_id, Integer id, Integer type) {
        jsonBase = new JsonBase();
        if(type == 1){
            //记忆中->掌握
            wordMapper.setWordIsMemorized(id);

        } else if(type == 2){
            //未背->掌握，该id是words表的id
            wordMapper.addWordAndSetMemorized(learner_id,wordbook_id,id);
        } else if(type == 3){
            //掌握->未背 重新学习
            wordMapper.removeMemorizeWord(id);
        } else{
            jsonBase.create(444,"参数出错");
        }
        jsonBase.create(200,"success");
        return jsonBase;
    }

    /**
     * 获取需要复习的单词，30一组
     * @param learner_id
     * @param wordbook_id
     * @param pageUtil
     * @return
     */
    @Override
    public WordJson getReviewWords(Integer learner_id, Integer wordbook_id, PageUtil pageUtil) {
        wordJson = new WordJson();
        List<Word> words = wordMapper.getReviewWords(learner_id,wordbook_id,pageUtil);
        if(words.size()>0){
            wordJson.create(200,"success");
            wordJson.setWords(words);
        }else {
            wordJson.create(507,"无需复习");
        }
        return wordJson;
    }

    /**
     * 批量插入新词
     * @param wordbook_id
     * @param learner_id
     * @param ids
     * @return
     */
    @Override
    public WordJson insertWords(Integer wordbook_id, Integer learner_id, List<Long> ids) {
        wordJson = new WordJson();
        StringBuilder sql = new StringBuilder();
        nextTimeUtils = new NextTimeUtils();
        String next_time = nextTimeUtils.getNextTime(1, Calendar.getInstance());

        sql.append("insert into memorize(learner_id,wordbook_id,word_id,next_time,created,modified) values ");
        for(Long id : ids){
            sql.append("(").append(learner_id).append(",").append(wordbook_id).append(",").append(id).append(",")
                    .append("\""+next_time+"\"").append(",").append("NOW(),NOW()").append("),");
        }

        sql.setLength(sql.length() - 1);
        Integer count = wordMapper.insertWords(sql.toString());
        if(count > 0){
            //更新学习数量
            wordMapper.updateLearnCount(wordbook_id,learner_id,count);
            wordJson.create(200,"success");
            return wordJson;

        }else {
            wordJson.create(505,"新词保存失败");
        }
        return wordJson;
    }

    /**
     * 更新
     * @param words
     * @return
     */
    @Override
    public WordJson updateWords(List<Word> words) {

        wordJson = new WordJson();
        StringBuilder sql = new StringBuilder();
        nextTimeUtils = new NextTimeUtils();
        //ids的
        StringBuilder ids = new StringBuilder();
        sql.append("UPDATE memorize SET next_time = CASE ")
                .append("WHEN memorized_count = 1 THEN ").append(" \""+nextTimeUtils.getNextTime(2, Calendar.getInstance())+"\" ")
                .append("WHEN memorized_count = 2 THEN ").append(" \""+nextTimeUtils.getNextTime(3, Calendar.getInstance())+"\" ")
                .append("WHEN memorized_count = 3 THEN ").append(" \""+nextTimeUtils.getNextTime(4, Calendar.getInstance())+"\" ")
                .append("WHEN memorized_count = 4 THEN ").append(" \""+nextTimeUtils.getNextTime(5, Calendar.getInstance())+"\" ")
                .append("WHEN memorized_count = 5 THEN ").append(" \""+nextTimeUtils.getNextTime(6, Calendar.getInstance())+"\" ")
                .append("WHEN memorized_count = 6 THEN ").append(" \""+nextTimeUtils.getNextTime(7, Calendar.getInstance())+"\" ")
                .append(" end,").append("memorized_count = memorized_count + 1 WHERE id IN (");
        for(Word word : words){
            ids.append(word.getId()+",");
        }

        ids.setLength(ids.length() - 1);
        sql.append(ids.toString()+");");
        Integer count = wordMapper.updateReviewWords(sql.toString());

        //如果全部更新成功
        if(count == words.size()){
            wordJson.create(200,"success");
        }else {
            wordJson.create(506,"记忆表单词更新失败");
        }

        return wordJson;
    }


}
