package com.mailautomation.mailautomation.requests;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.entities.Mails;

public class Requests {
    private String manager_name;
    private String manager_surmame;
    private Mails mail_info;

    public Requests() {
    }

    public Requests(String manager_name, String manager_surmame, Mails mail_info) {
        this.manager_name = manager_name;
        this.manager_surmame = manager_surmame;
        this.mail_info = mail_info;
    }

    public Mails getMail_info() {
        return mail_info;
    }

    public void setMail_info(Mails mail_info) {
        this.mail_info = mail_info;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getManager_surmame() {
        return manager_surmame;
    }

    public void setManager_surmame(String manager_surmame) {
        this.manager_surmame = manager_surmame;
    }
}
