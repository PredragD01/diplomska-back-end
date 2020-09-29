package com.mailautomation.mailautomation.service;

import com.mailautomation.mailautomation.entities.KeyWords;
import com.mailautomation.mailautomation.repositories.KeyWordsRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyWordServiceImpl implements KeyWordService {

    @Autowired
    private KeyWordsRepositoryService krs;

    @Override
    public List<KeyWords> getKeys() {
        return krs.getKeys();
    }

    @Override
    public void updateKeys(KeyWords keyWords) {
        krs.updateKeys(keyWords);
    }

    @Override
    public void deleteKey(int id) {
        krs.deleteKey(id);
    }

    @Override
    public void add(KeyWords keyWords) {
        krs.add(keyWords);
    }
}
