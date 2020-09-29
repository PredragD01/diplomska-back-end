package com.mailautomation.mailautomation.entities;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "documents")
public class Documents {

    @Id
    @Column
    private int id;

    @Column
    private byte[] document_path;

    @Column
    private int owner_doc;

    @Column
    private String date_created;

    @Column
    private String title;

    public Documents() {
    }

    public Documents(String date_created,int id, byte[] document_path, int owner_doc, String title) {
        this.id = id;
        this.document_path = document_path;
        this.owner_doc = owner_doc;
        this.date_created = date_created;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getDocument_path() {
        return document_path;
    }

    public void setDocument_path(byte[] document_path) {
        this.document_path = document_path;
    }

    public int getOwner_doc() {
        return owner_doc;
    }

    public void setOwner_doc(int owner_doc) {
        this.owner_doc = owner_doc;
    }
}
