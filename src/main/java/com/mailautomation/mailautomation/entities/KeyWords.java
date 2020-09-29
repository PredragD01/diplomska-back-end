package com.mailautomation.mailautomation.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "key_words")

public class KeyWords {
    @Id
    @Column
    private int id;
    @Column
    private String key_word;
    @Column
    private String value_word;

    public String getKey_word() {
        return key_word;
    }

    public KeyWords() { }
    public KeyWords(String key_word, String value_word,int id) {
        this.key_word = key_word;
        this.value_word = value_word;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public String getValue_word() {
        return value_word;
    }

    public void setValue_word(String value_word) {
        this.value_word = value_word;
    }
}
