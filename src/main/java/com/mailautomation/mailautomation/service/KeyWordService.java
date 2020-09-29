package com.mailautomation.mailautomation.service;

import com.mailautomation.mailautomation.entities.KeyWords;

import java.util.List;

public interface KeyWordService {
    List<KeyWords> getKeys();
    void updateKeys(KeyWords keyWords);
    void deleteKey(int id);
    void add(KeyWords keyWords);

}
