package com.ahuiali.word.service;

import com.ahuiali.word.json.JsonBase;
import com.ahuiali.word.json.WordbookJson;
import com.ahuiali.word.pojo.Wordbook;

public interface WordbookService {
    WordbookJson getWordbooks();

    WordbookJson getWordbookDetail(Integer id, Integer learner_id);

    WordbookJson getWords(Integer id, int curr, int size);

    JsonBase addWordbook(Integer learnerId, Integer wordbook_id) throws Exception;

    WordbookJson findMyWordbooks(Integer learnerId);

    JsonBase updateWordbookPlan(Integer learnerId, Integer wordbook_id);

    WordbookJson myMemorizingWordbook(Integer learnerId);

    Integer findReviewCount( Integer learnerId, Integer wordbook_id);

    WordbookJson getMemorizingWordbookAndReviewCount(Integer learnerId);
}
