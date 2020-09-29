package com.mailautomation.mailautomation.controllers;


import com.mailautomation.mailautomation.entities.KeyWords;
import com.mailautomation.mailautomation.service.KeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*")
@RestController
public class KeyWordsController {

    @Autowired
    private KeyWordService ks;

    @PostMapping("/keywords")
    public void addKey(@RequestBody KeyWords keyWords){
        ks.add(keyWords);
    }

    @PutMapping("/keywords")
    public void updateKeywords(@RequestBody KeyWords keyWords) throws NoSuchFieldException {
        ks.updateKeys(keyWords);
    }

    @DeleteMapping("/keywords/{id}")
    public void deleteKey(@PathVariable int id){
        ks.deleteKey(id);
    }

    @GetMapping("/keywords")
    public List<KeyWords> getKeys(){
        return ks.getKeys();
    }
}
