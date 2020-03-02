package com.ahuiali.word.service.impl;

import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.NotebookJson;
import com.ahuiali.word.json.WordJson;
import com.ahuiali.word.mapper.NotebookMapper;
import com.ahuiali.word.pojo.Notebook;
import com.ahuiali.word.pojo.Word;
import com.ahuiali.word.service.NotebookService;
import com.ahuiali.word.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotebookServiceImpl  implements NotebookService {

    @Autowired
    NotebookMapper notebookMapper;

    @Autowired
    JsonBase jsonBase;

    @Autowired
    WordJson wordJson;

    @Autowired
    NotebookJson notebookJson;

    /**
     * 根据用户id查询所有生词本
     * @param learner_id
     * @return
     */
    @Override
    public NotebookJson findAllNotebookByLearnerId(Integer learner_id) {

        notebookJson = new NotebookJson();

        //根据id查询所有生词本
        List<Notebook> notebooks = notebookMapper.findAllNotebookByLearnerId(learner_id);
        //大于0则返回
        if(notebooks.size() > 0){
            notebookJson.create(200,"success");
            notebookJson.setNotebooks(notebooks);
        } else{
            notebookJson.create(600,"生词本列表为空");
        }

        return notebookJson;
    }

    /**
     * 添加生词本
     * @param notebook 生词本
     * @return
     */
    @Override
    public NotebookJson addNotebook(Notebook notebook) {
        notebookJson = new NotebookJson();

        //返回影响条数
        Integer total = notebookMapper.addNotebook(notebook);
        if(total > 0){
            //大于0说明插入成功
            notebookJson.create(200,"success");
//            notebookJson.setNotebooks((List<Notebook>) notebook);
        }else{
            //小于0说明插入失败
            notebookJson.create(601,"生词本添加失败");
        }

        return notebookJson;
    }

    /**
     * 根据生词本id删除生词本
     * @param id
     * @return
     */
    @Override
    public JsonBase removeNotebook(Integer id) {
        jsonBase = new JsonBase();

        Integer total = notebookMapper.removeNotebook(id);
        if(total > 0){
            //大于0说明删除成功
            jsonBase.create(200,"success");
        }else{
            //小于0说明删除失败
            jsonBase.create(602,"生词本删除失败");
        }
        return null;
    }

    /**
     * 删除生词本的某个单词
     * @param id 生词本-单词表的id
     * @return
     */
    @Override
    public JsonBase removeWord(Integer id) {
        jsonBase = new JsonBase();

        Integer total = notebookMapper.removeWord(id);
        if(total > 0){
            //大于0说明删除成功
            jsonBase.create(200,"success");
        }else{
            //小于0说明删除失败
            jsonBase.create(603,"生词本单词删除失败");
        }
        return jsonBase;
    }

    /**
     * 为生词本添加单词
     * @param notebook_id 生词本id
     * @param word 单词
     * @return jsonbase
     */
    @Override
    public JsonBase addWord(Integer notebook_id, String word) {
        jsonBase = new JsonBase();

        Integer total = notebookMapper.addWord(notebook_id,word);
        if(total > 0){
            //大于0说明添加成功
            jsonBase.create(200,"success");
        }else{
            //小于0说明添加失败
            jsonBase.create(604,"生词本单词添加失败");
        }
        return jsonBase;
    }

    /**
     * 查询某生词本的单词，分页
     * @param notebook_id
     * @param pageUtil
     * @return
     */
    @Override
    public WordJson listWord(Integer notebook_id, PageUtil pageUtil) {
        wordJson = new WordJson();
        //获取单词列表
        List<Word> words = notebookMapper.listWords(notebook_id,pageUtil);
        //不为空返回200
        if(words.size()>0){
            wordJson.create(200,"success",words);
        } else{
            //为空返回605
            wordJson.create(605,"生词本单词为空",null);
        }
        return wordJson;
    }


    /**
     * 修改生词本名称
     * @param name
     * @param learner_id
     * @return
     */
    @Override
    public NotebookJson editNotebook(String name, Integer learner_id) {
        notebookJson = new NotebookJson();
        Integer count = notebookMapper.editNotebookName(name,learner_id);
        if(count > 0){
            notebookJson.create(200,"success");
        }else {
            notebookJson.create(606,"生词本修改失败");
        }
        return notebookJson;
    }


}












