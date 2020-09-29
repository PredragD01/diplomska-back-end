package com.mailautomation.mailautomation.controllers;

import com.mailautomation.mailautomation.dtos.DocumentDto;
import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.entities.Mails;
import com.mailautomation.mailautomation.requests.Requests;
import com.mailautomation.mailautomation.service.FileService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
public class FileContoller {

    @Autowired
    private FileService fs;

    @PostMapping("/createPDF")
    public void createPdf(@RequestBody Requests req) throws IOException, InvalidFormatException, MessagingException {
        fs.createPDF(req);
    }

    @GetMapping(value = "/getPDF/{id}")
    public byte[] getPdf(@PathVariable("id") int id){
        System.out.println(id);
        return fs.getPDF(id);
    }

    @PostMapping(value = "/decline")
    public void decline(@RequestBody Mails mail){
        fs.decilne(mail);
    }

    @PostMapping(value = "/getAllDocuments")
    public List<DocumentDto> getAllDocuments(@RequestBody EmployeeDto empdto){
        return fs.getAllDocuments(empdto);
    }
}
