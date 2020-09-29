package com.mailautomation.mailautomation.repositories;


import com.mailautomation.mailautomation.entities.KeyWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class KeyWordsRepositoryServiceImpl implements KeyWordsRepositoryService {

    @Autowired
    private EntityManager em;

    @Transactional
    @Override
    public List<KeyWords> getKeys() {
        Query q = em.createNativeQuery("SELECT * FROM key_words", KeyWords.class);
        List<KeyWords> kwlist = q.getResultList();
        return kwlist;
    }

    @Transactional
    @Override
    public void updateKeys(KeyWords keyWords) {
        em.merge(keyWords);
    }

    @Transactional
    @Override
    public void deleteKey(int id) {
        KeyWords keyWords = em.find(KeyWords.class, id);
        em.remove(keyWords);
    }

    @Transactional
    @Override
    public void add(KeyWords keyWords) {
        em.persist(keyWords);
    }
}
