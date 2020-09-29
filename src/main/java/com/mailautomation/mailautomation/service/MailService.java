package com.mailautomation.mailautomation.service;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.entities.KeyWords;
import com.mailautomation.mailautomation.entities.Mails;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface MailService {

    List<Mails> getMailsForUser(EmployeeDto employeeDto) throws MessagingException, IOException;
    void resendToManager(Mails email) throws NoSuchFieldException;
    void declineMail(Mails mail) throws MessagingException;
    void undefinedMail(int id, String to);
    void setKeyWords(KeyWords keyWords);
    void sendEmail(String message, String sender) throws MessagingException;

}
