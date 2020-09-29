package com.mailautomation.mailautomation.repositories;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.entities.KeyWords;
import com.mailautomation.mailautomation.entities.Mails;

import java.util.List;

public interface MailRepositoryService {

    List<Mails> getMailsForUser(EmployeeDto employeeDto);
    void saveMails(Mails mails);
    void editMails(Mails mails);
    List<KeyWords> getKeys();
    void deleteEmail(int id);
    void setKeyWords(KeyWords keyWords);
}
