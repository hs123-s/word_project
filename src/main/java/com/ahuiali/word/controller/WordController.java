package com.ahuiali.word.controller;


import com.ahuiali.word.json.WordJson;
import com.ahuiali.word.mapper.WordMapper;
import com.ahuiali.word.pojo.Word;
import com.ahuiali.word.service.WordService;
import com.ahuiali.word.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 单词控制器
 * Created by shkstart on 2019/9/18
 */
@Controller
@RequestMapping("/words")
public class WordController {

    @Autowired
    WordService wordService;

    @Autowired
    WordJson wordJson;



    @RequestMapping(value = "/getWords/",produces = "application/json;charset=utf-8;")
    public @ResponseBody WordJson getWords(@RequestBody PageUtil pageUtil){

        wordJson = wordService.getWords(1,pageUtil);
        return wordJson;

    }



    @RequestMapping(value = {"/goto"})
    public String gotoMemorize(){
        return "/word/memorize";
    }

    /**
     * 返回复习词汇
     * @param wordbook_id
     * @param pageUtil
     * @param session
     * @return
     */
    @RequestMapping(value = "/getReviewWords/{wordbook_id}" ,produces = "application/json;charset=utf-8;")
    public @ResponseBody WordJson getReviewWords(@PathVariable("wordbook_id") Integer wordbook_id,
                                                 @RequestBody PageUtil pageUtil,
                                                 HttpSession session){
        Integer learner_id = (Integer) session.getAttribute("learnerId");
        //获取所有需复习单词(分页)
        pageUtil.renew();
        wordJson = wordService.getReviewWords(learner_id,wordbook_id,pageUtil);

        return wordJson;

    }



    /**
     * 返回新词15个
     */


    /**
     * 添加单词到记忆库（批量）
     */

}
