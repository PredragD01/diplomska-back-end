package com.mailautomation.mailautomation.service;

import com.mailautomation.mailautomation.dtos.DocumentDto;
import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.entities.Documents;
import com.mailautomation.mailautomation.entities.Mails;
import com.mailautomation.mailautomation.requests.Requests;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface FileService {
    void createPDF(Requests req) throws IOException, InvalidFormatException, MessagingException;
    byte[] getPDF(int id);
    List<DocumentDto> getAllDocuments(EmployeeDto employeeDto);
    void decilne(Mails mails);
}
