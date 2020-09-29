package com.mailautomation.mailautomation.controllers;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.entities.KeyWords;
import com.mailautomation.mailautomation.entities.Mails;
import com.mailautomation.mailautomation.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
public class MailsController{

    @Autowired
    private MailService service;

    @PostMapping("/mails")
    public List<Mails> GetMailsForUser(@RequestBody EmployeeDto employeeDto) throws IOException, MessagingException {
        return service.getMailsForUser(employeeDto);
    }

    @PostMapping("/resend")
    public void sendToManager(@RequestBody Mails mails) throws NoSuchFieldException {
        service.resendToManager(mails);
    }

    @GetMapping("/declineMail")
    public void decline(Mails mail) throws MessagingException {
        service.declineMail(mail);
    }

    @GetMapping("/undefinedMail/{id}/{to}")
    public void undefinedMail(@PathVariable("id") int id, @PathVariable("to") String to){
        service.undefinedMail(id, to);
    }

    @PostMapping("/setNewKeys")
    public void setKeys(@RequestBody KeyWords keyWords){
        service.setKeyWords(keyWords);
    }

}
