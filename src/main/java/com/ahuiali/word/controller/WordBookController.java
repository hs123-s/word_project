package com.ahuiali.word.controller;

import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.WordJson;
import com.ahuiali.word.json.WordbookJson;

import com.ahuiali.word.pojo.Word;
import com.ahuiali.word.pojo.Wordbook;
import com.ahuiali.word.service.WordService;
import com.ahuiali.word.service.WordbookService;
import com.ahuiali.word.utils.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 词书控制器
 */
@Controller
@RequestMapping("/wordbook")
public class WordBookController {

    @Autowired
    WordbookService wordbookService;

    @Autowired
    WordService wordService;

    @Autowired
    WordJson wordJson;

    @Autowired
    WordbookJson wordbookJson;

    @Autowired
    JsonBase jsonBase;

    //跳转至词库
    @RequestMapping("/gotoWordbook")
    public String gotoWordbook(){
        return "/wordbook/wordbooks";
    }

    //跳转至词书页面
    @RequestMapping("/gotoWordbookDetail")
    public String gotoWordbookDetail(){

        return "/wordbook/wordbookDetail";
    }

    //跳转到我的词书的页面
    @RequestMapping("/gotoMyWordbooks")
    public String gotoMyWordbooks(){

        return "/wordbook/myWordbooks";
    }

    //跳转到我的某个词书的页面
    @RequestMapping("/gotoMyWordbookDetail")
    public String gotoMyWordbookDetail(){

        return "/wordbook/myWordbookDetail";
    }

    //跳转到复习页面
    @RequestMapping("/gotoReview")
    public String gotoReview(){

        return "/word/review";
    }

    /**
     * 获取所有词书
     * @return
     */
    @RequestMapping(value = "/",produces = "application/json;charset=utf-8;")
    public @ResponseBody WordbookJson getWordbooks(){
        wordbookJson = wordbookService.getWordbooks();
        return wordbookJson;
    }

    /**
     * 根据词书id返回细节
     * @param id
     * @param
     * @return
     */
    @RequestMapping(value = "/detail/{id}")
    public @ResponseBody WordbookJson getWordbookDetail(@PathVariable("id") String id,
                                                        HttpSession session){
        //获取学习者id
        Integer learner_id = (Integer) session.getAttribute("learnerId");
        wordbookJson = wordbookService.getWordbookDetail(Integer.parseInt(id),learner_id);
        return wordbookJson;
    }


    /**
     * 返回size个该词书的单词
     * @param id
     * @return
     */
    @RequestMapping(value = "/showWords/{id}",produces = "application/json;charset=utf-8;")
    public @ResponseBody WordJson showWords(@PathVariable("id") Integer id,
                                            @RequestBody  PageUtil pageUtil){
        //刷新offset
        pageUtil.renew();

        wordJson = wordService.getWords(id,pageUtil);
        return wordJson;
    }

    /**
     * 为用户添加词书，并将其设置为当前词书
     * @param wordbook_id
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addWordbook/{wordbook_id}",produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase addWordbook(@PathVariable("wordbook_id") Integer wordbook_id, HttpSession session) throws Exception {
        //获取学习者id
        Integer learnerId = (Integer) session.getAttribute("learnerId");

        jsonBase = wordbookService.addWordbook(learnerId,wordbook_id);

        return jsonBase;
    }

    /**
     * 修改当前计划
     * @param wordbook_id
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/changeWordbook/{wordbook_id}")
    public String  changeWordbook(@PathVariable("wordbook_id") Integer wordbook_id, HttpSession session){
        //获取学习者id
        Integer learnerId = (Integer) session.getAttribute("learnerId");

        jsonBase = wordbookService.updateWordbookPlan(learnerId,wordbook_id);

        return "redirect:/wordbook/gotoMyWordbooks";
    }

    /**
     * 查询我的词书
     * @param session
     * @return
     */
    @RequestMapping(value = "/myWordbooks" , produces = "application/json;charset=utf-8;")
    public @ResponseBody WordbookJson myWordbooks(HttpSession session){
        //获取学习者id

       Integer learnerId = (Integer) session.getAttribute("learnerId");

        //session中保存我的词书
        wordbookJson = wordbookService.findMyWordbooks(learnerId);

        return wordbookJson;
    }

    /**
     * 查询我的词书(-)
     * @param session
     * @return
     */
    @RequestMapping(value = "/index" )
    public String  myMemorizingWordbooks(HttpSession session, Map map){
        //获取学习者id

//       Integer learnerId = (Integer) session.getAttribute("learnerId");
        Integer learnerId = 21;
        Integer review = 0;



//            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
//            Date date = new Date();
//            String dateStr = sdf.format(date);
//            review = wordbookService.findReviewCount(learnerId,wordbook.getId());


        return "index";
    }



    /**
     * 我的词书的单词类型
     * @param wordbook_id 词书id
     * @param wordsType 返回单词类型，1为未背，2为记忆中，3为已掌握
     * @param pageUtil 分页类
     * @param session 用于获取learner_id
     * @return
     */
    @RequestMapping(value = "/myWordbook/words/{wordbook_id}/{wordsType}" , produces = "application/json;charset=utf-8;")
    public @ResponseBody WordJson myWordbookWords(
            @PathVariable("wordbook_id") Integer wordbook_id,
            @PathVariable("wordsType") Integer wordsType,
            @RequestBody PageUtil pageUtil,
            HttpSession session){
        Integer learner_id = (Integer) session.getAttribute("learnerId");
        pageUtil.renew();
        wordJson = wordService.myWordbookWords(wordbook_id,learner_id,pageUtil,wordsType);
        return wordJson;
    }

    /**
     *  单词类型转移
     *
     * @param wordbook_id
     * @param id 未背->掌握时id为words的id，其余为记忆表的id
     * @param type 记忆中->掌握 : 1, 未背->掌握 : 2, 掌握->未背 : 3
     * @param session
     * @return
     */
    @RequestMapping("/myWordbook/words/wordTypeChange/{wordbook_id}/{id}/{type}")
    public @ResponseBody JsonBase wordTypeChange(@PathVariable("wordbook_id") Integer wordbook_id,
                                                 @PathVariable("id") Integer id,
                                                 @PathVariable("type") Integer type,
                                                 HttpSession session){
        Integer learner_id = (Integer) session.getAttribute("learnerId");
        jsonBase = wordService.wordTypeChange(learner_id,wordbook_id,id,type);
        return jsonBase;
    }


    /**
     * 将新词加入记忆表中
     * @param wordbook_id
     * @param session
     * @return
     */
    @RequestMapping(value = "/myWordbook/insert/{wordbook_id}" , produces = "application/json;charset=utf-8;")
    public @ResponseBody WordJson insert(@PathVariable("wordbook_id") Integer wordbook_id,
                                                  @RequestBody List<Long> ids,
                                                  HttpSession session){
        Integer learner_id = (Integer) session.getAttribute("learnerId");
        wordJson = new WordJson();
        wordJson = wordService.insertWords(wordbook_id,learner_id,ids);
        return wordJson;
    }

    /**
     * 更新记忆表的单词
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/myWordbook/review" , produces = "application/json;charset=utf-8;")
    public @ResponseBody WordJson review(@RequestBody List<Word> words){
        wordJson = new WordJson();
        wordJson = wordService.updateWords(words);
        return wordJson;
    }


}
















