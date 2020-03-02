package com.ahuiali.word.service;


import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.NotebookJson;
import com.ahuiali.word.json.WordJson;
import com.ahuiali.word.pojo.Notebook;
import com.ahuiali.word.pojo.Word;
import com.ahuiali.word.utils.PageUtil;

import java.util.List;

public interface NotebookService {


    NotebookJson findAllNotebookByLearnerId(Integer learner_id);

    NotebookJson addNotebook(Notebook notebook);

    JsonBase removeNotebook(Integer id);

    JsonBase removeWord(Integer id);

    JsonBase addWord(Integer notebook_id, String word);

    WordJson listWord(Integer notebook_id, PageUtil pageUtil);

    NotebookJson editNotebook(String trim, Integer learner_id);
}
