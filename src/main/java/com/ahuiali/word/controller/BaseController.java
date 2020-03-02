package com.ahuiali.word.controller;

import com.ahuiali.word.json.WordbookJson;
import com.ahuiali.word.pojo.Wordbook;
import com.ahuiali.word.service.WordbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/")
public class BaseController {


    @Autowired
    WordbookService wordbookService;

    @Autowired
    WordbookJson wordbookJson;

    /**
     * 主页
     * @return
     */
    @RequestMapping("/")
    public String gotoIndex(){

        return "/index";
    }

    /**
     * 主页初始化
     * 查询我当前记忆的词书
     * @param session
     * @return
     */
    @RequestMapping(value = "/index", produces = "application/json;charset=utf-8;" )
    public @ResponseBody WordbookJson
    indexInit(HttpSession session){
        //获取学习者id
       Integer learnerId = (Integer) session.getAttribute("learnerId");
        wordbookJson = wordbookService.getMemorizingWordbookAndReviewCount(learnerId);
        return wordbookJson;
    }
}
