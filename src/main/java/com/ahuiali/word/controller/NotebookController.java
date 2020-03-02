package com.ahuiali.word.controller;

import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.NotebookJson;
import com.ahuiali.word.json.WordJson;
import com.ahuiali.word.pojo.Notebook;
import com.ahuiali.word.pojo.Word;
import com.ahuiali.word.service.NotebookService;
import com.ahuiali.word.utils.PageUtil;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notebook")
public class NotebookController {

    @Autowired
    NotebookJson notebookJson;

    @Autowired
    JsonBase jsonBase;

    @Autowired
    WordJson wordJson;

    @Autowired
    NotebookService notebookService;

    /**
     * 跳转至我的生词本界面
     * @return
     */
    @RequestMapping(value = "/gotoNotebook")
    public String myNotebook(HttpSession session, Map map){

        return "/notebook/notebooks";
    }

    /**
     * 查询我的生词本
     * @return
     */
    @RequestMapping(value = "/myNotebookJson")
    public @ResponseBody NotebookJson myNotebookJSON(HttpSession session){

        //获取学习者id

       Integer learner_id = (Integer) session.getAttribute("learnerId");

        notebookJson = notebookService.findAllNotebookByLearnerId(learner_id);

        return notebookJson;
    }

    /**
     * 修改生词本
     * @param session
     * @return
     */
    @RequestMapping(value = "/editNotebook" )
    public String editNotebook(HttpSession session,
                               @RequestParam(value = "notebookName",required = false) String name,
                               @RequestParam(value = "id",required = false) Integer id){


        notebookJson = notebookService.editNotebook(name.trim(),id);
        //重定向
        return "redirect:/notebook/gotoNotebook";
    }


    /**
     * 新增生词本
     * @param session
     * @return
     */
    @RequestMapping(value = "/addNotebook" )
    public String addNotebook(HttpSession session,
                              @RequestParam(value = "notebookName",required = false) String name){
        //获取学习者id
       Integer learnerId = (Integer) session.getAttribute("learnerId");
        Notebook notebook = new Notebook();
        notebook.setLearner_id(learnerId);
        notebook.setName(name.trim());
        notebookJson = notebookService.addNotebook(notebook);

        //前端自动判断，若添加成功，则在生词本列表追加，否则显示‘添加生词本失败信息’，code为601(*)


        //重定向
        return "redirect:/notebook/gotoNotebook";
    }

    /**
     * 删除生词本(*)
     * @param session
     * @param id 生词本id
     * @return
     */
    @RequestMapping(value = "/removeNotebook/{id}" ,produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase removeNotebook(HttpSession session, @PathVariable("id") Integer id){
        jsonBase = notebookService.removeNotebook(id);
        return jsonBase;
    }

    /**
     * 为生词本添加单词
     * @param word  单词
     * @param id 生词本id
     * @return
     */
    @RequestMapping(value = "/addWord/{word}/{id}" ,produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase addWord(@PathVariable("word") String  word,
                                          @PathVariable("id") Integer id){
        jsonBase = notebookService.addWord(id,word);
        return jsonBase;
    }

    /**
     * 从生词本中移除单词
     * @param id 生词本某单词主键id
     * @return
     */
    @RequestMapping(value = "/removeWord/{id}" ,produces = "application/json;charset=utf-8;")
    public @ResponseBody JsonBase removeWord(@PathVariable("id") Integer id){
        jsonBase = notebookService.removeWord(id);
        return jsonBase;
    }


    /**
     * 列出该生词本的单词（分页）
     * @param notebook_id
     * @param pageUtil
     * @return
     */
    @RequestMapping(value = "/listWords/{notebook_id}")
    public @ResponseBody WordJson listWords(@PathVariable("notebook_id") Integer notebook_id, @RequestBody PageUtil pageUtil){

        pageUtil.renew();
        WordJson wordJson = notebookService.listWord(notebook_id,pageUtil);

        return wordJson;
    }

    /**
     * 跳转到生词本详细页面
     * @return
     */
    @RequestMapping(value = "/gotoNoteDetail")
    public String  gotoNoteDetail(){
        return "/notebook/notebookDetail";
    }



}
