package com.mailautomation.mailautomation.repositories;

import com.mailautomation.mailautomation.entities.KeyWords;

import java.util.List;

public interface KeyWordsRepositoryService {
    List<KeyWords> getKeys();
    void updateKeys(KeyWords keyWords);
    void deleteKey(int id);
    void add(KeyWords keyWords);
}
