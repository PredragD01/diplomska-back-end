package com.mailautomation.mailautomation.entities;

import javax.persistence.Id;
import org.springframework.data.relational.core.mapping.Column;
import javax.persistence.*;

@Entity(name = "mails")
public class Mails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private int id;

    @Column
    private String subject_mail;

    @Column
    private String body_mail;

    @Column
    private String sender_adress;

    @Column
    private String date_sent;

    @Column
    private int at_employee;

    @Column
    private String date_from;

    @Column
    private String date_to;

    @Column
    private String number_of_days;

    @Column
    private int status_;

    public Mails(){};

    public Mails(String subject_mail, String body_mail, String sender_adress, String date_sent, int at_employee,
                 String date_from, String date_to, String number_of_days, int status_) {
        this.subject_mail = subject_mail;
        this.body_mail = body_mail;
        this.sender_adress = sender_adress;
        this.date_sent = date_sent;
        this.at_employee = at_employee;
        this.date_from = date_from;
        this.date_to = date_to;
        this.number_of_days = number_of_days;
        this.status_ = status_;
    }

    public int getStatus() {
        return status_;
    }

    public void setStatus(int status_) {
        this.status_ = status_;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getNumber_of_days() {
        return number_of_days;
    }

    public void setNumber_of_days(String number_of_days) {
        this.number_of_days = number_of_days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_mail() {
        return subject_mail;
    }

    public void setSubject_mail(String subject_mail) {
        this.subject_mail = subject_mail;
    }

    public String getBody_mail() {
        return body_mail;
    }

    public void setBody_mail(String body_mail) {
        this.body_mail = body_mail;
    }

    public String getSender_adress() {
        return sender_adress;
    }

    public void setSender_adress(String sender_adress) {
        this.sender_adress = sender_adress;
    }

    public String getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(String date_sent) {
        this.date_sent = date_sent;
    }

    public int getAt_employee() {
        return at_employee;
    }

    public void setAt_employee(int at_employee) {
        this.at_employee = at_employee;
    }
}
