package com.ahuiali.word.controller;

import com.ahuiali.word.json.WordEctDetailJson;
import com.ahuiali.word.json.WordEctJson;
import com.ahuiali.word.mapper.WordEctMapper;
import com.ahuiali.word.mapper.WordMapper;
import com.ahuiali.word.service.WordEctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 单词总表
 */
@Controller
@RequestMapping("/wordect")
public class WordEctController {

    @Autowired
    WordEctJson wordEctJson;

    @Autowired
    WordEctDetailJson wordEctDetailJson;

    @Autowired
    WordEctService wordEctService;

    @RequestMapping("")
    public String gotoDetail(){
        return "/word/wordDetail";
    }

    @RequestMapping("/gotoSearch")
    public String gotoSearch(){
        return "/word/search";
    }

    /**
     * 通过单词前缀来模糊查询单词，自动提示效果
     * @param wordpre
     * @return
     */
    @RequestMapping(value = "/input/{word}",produces = "application/json;charset=utf-8;")
    public @ResponseBody
    WordEctJson input(@PathVariable("word") String wordpre){

        wordEctJson = wordEctService.getWordsByPre(wordpre);

        return wordEctJson;
    }

    /**
     * 查询单词详细信息JSON
     * @param word
     * @param session
     * @return
     */
    @RequestMapping(value = "/findWordDetailJson/{word}",produces = "application/json;charset=utf-8;")
    public @ResponseBody WordEctDetailJson findWordDetailJSON(@PathVariable("word") String word, HttpSession session){

        Integer learner_id = (Integer) session.getAttribute("learnerId");
        wordEctDetailJson = wordEctService.findWordDetail(word,learner_id);

        return wordEctDetailJson;
    }


    /**
     * 查询单词详细信息页面

     * @return
     */
    @RequestMapping(value = "/gotoWordDetail")
    public String  findWordDetailString(){


        return "/word/detail";
    }

    /**
     * 普通查询，返回基本数据
     * @param word
     * @return
     */
    @RequestMapping(value = "/findWord/{word}",produces = "application/json;charset=utf-8;")
    public @ResponseBody WordEctJson findWord(@PathVariable("word") String word,HttpSession session){
        Integer learner_id = (Integer) session.getAttribute("learnerId");
        wordEctJson = wordEctService.findWord(word,learner_id);

        return wordEctJson;
    }

}
