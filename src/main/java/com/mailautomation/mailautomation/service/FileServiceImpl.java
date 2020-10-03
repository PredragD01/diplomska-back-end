package com.mailautomation.mailautomation.service;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.mailautomation.mailautomation.dtos.DocumentDto;
import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.entities.Documents;
import com.mailautomation.mailautomation.entities.Employee;
import com.mailautomation.mailautomation.entities.Mails;
import com.mailautomation.mailautomation.repositories.EmployeeRepositoryService;
import com.mailautomation.mailautomation.repositories.FileRepositoryService;
import com.mailautomation.mailautomation.repositories.MailRepositoryService;
import com.mailautomation.mailautomation.requests.Requests;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepositoryService frs;

    @Autowired
    private EmployeeRepositoryService ers;

    @Autowired
    private MailRepositoryService mrs;

    @Autowired
    private MailService ms;

    @Override
    public void createPDF(Requests req) throws IOException, InvalidFormatException, MessagingException {
        //brishi go mejlot
//        req.getMail_info().setAt_employee(4);
//        mrs.editMails(req.getMail_info());
        if(req.getMail_info().getSubject_mail().equals("Годишен одмор")){
            generateGodishen(req);
        }else{
            generateFreeDays(req);
        }
    }

    @Override
    public byte[] getPDF(int id) {
        return frs.getPDF(id);
    }

    @Override
    public List<DocumentDto> getAllDocuments(EmployeeDto employeeDto) {
        List<Documents> list = frs.getAllDocuments();
        List<DocumentDto> ddto = new ArrayList<>();
        if(employeeDto.getDeptInfo().getId() == 1){
            for(Documents doc : list){
                Employee emp = ers.getEmployeeById(doc.getOwner_doc());
                DocumentDto dto = new DocumentDto(emp,doc);
                ddto.add(dto);
            }
        }else{
            for(Documents doc : list){
                if(doc.getOwner_doc() == employeeDto.getEmployee().getId()){
                    Employee emp = ers.getEmployeeById(doc.getOwner_doc());
                    DocumentDto dto = new DocumentDto(emp,doc);
                    ddto.add(dto);
                }
            }
        }

        return ddto;
    }

    @Override
    public void decilne(Mails mails) {
        mrs.deleteEmail(mails.getId());
        try {
            ms.sendEmail("Вашето барање е одбиено од страна на Вашиот тим менаџер", mails.getSender_adress());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    ///
    public void generateGodishen(Requests req) throws IOException, InvalidFormatException, MessagingException {
        InputStream fis  = new FileInputStream("templates/godishenodmorNew.docx");
        XWPFDocument doc = new XWPFDocument(OPCPackage.open(fis));
        EmployeeDto employeeDto = ers.getEmployeeByEmail(req.getMail_info().getSender_adress());
        List<XWPFParagraph> paragraphList = doc.getParagraphs();
        for (XWPFParagraph p : paragraphList) {
            List<XWPFRun> runs = p.getRuns();

            for (XWPFRun r : runs) {
                String text = r.getText(0);
                System.out.println(text);
                if (text != null) {
                    if (text.contains("firstN")) {
                        r.setBold(true);
                        r.setText(employeeDto.getEmployee().getEmployee_name(), 0);
                    }
                    if (text.contains("secondN")) {
                        r.setBold(true);
                        r.setText(employeeDto.getEmployee().getEmployee_surname(), 0);
                    }
                    if (text.contains("city")) {
                        r.setBold(true);
                        r.setText(employeeDto.getEmployee().getCity(), 0);
                    }
                    if (text.contains("address")) {
                        r.setBold(true);
                        r.setText(employeeDto.getEmployee().getAddress_street(), 0);
                    }
                    if (text.contains("position")) {
                        r.setBold(true);
                        r.setText(employeeDto.getDeptInfo().getDepartment_name().toLowerCase(), 0);
                    }
                    if (text.contains("year")) {
                        r.setBold(true);
                        Calendar now = Calendar.getInstance();
                        int year = now.get(Calendar.YEAR);
                        String yearInString = String.valueOf(year);
                        r.setText(yearInString, 0);
                    }
                    if (text.contains("numberOfDays")) {
                        r.setBold(true);
                        r.setText(req.getMail_info().getNumber_of_days(), 0);
                    }
                    if (text.contains("dateFrom")) {
                        r.setBold(true);
                        r.setText(req.getMail_info().getDate_from(), 0);
                    }
                    if (text.contains("dateTo")) {
                        r.setBold(true);
                        r.setText(req.getMail_info().getDate_to(), 0);
                    }
                }
            }
        }
        //DOLNIOT DEL OD DOKUMENTOT
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            System.out.println(r.getText(0));
                            String text = r.getText(0);
                            if (text.contains("documented")) {
                                String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                                r.setBold(true);
                                r.setText(date, 0);
                            }
                            if (text.contains("employee")) {
                                String rabotnik = employeeDto.getEmployee().getEmployee_name() + " " + employeeDto.getEmployee().getEmployee_surname();
                                r.setBold(true);
                                r.setText(rabotnik, 0);
                            }
                            if (text.contains("secondN")) {
                                r.setBold(true);
                                r.setText(employeeDto.getEmployee().getEmployee_surname(), 0);
                            }
                            if (text.contains("projectManager")) {
                                String projectManager = req.getManager_name() + " " + req.getManager_surmame();
                                r.setBold(true);
                                r.setText(projectManager, 0);
                            }
                        }
                    }
                }
            }
        }
        
        PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");;
        OutputStream out = new FileOutputStream(new File("generatedPDFs/generated.pdf"));
        PdfConverter.getInstance().convert(doc, out, options);
        System.out.println("success");
        byte[] array = Files.readAllBytes(Paths.get("generatedPDFs/generated.pdf"));
        System.out.println(new String(array, 0));
        String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        frs.storePDF(array, employeeDto.getEmployee().getId(),date,req.getMail_info().getSubject_mail());
        ms.sendEmail("Вашето барање е прифатено од страна на тим менаџерот", employeeDto.getEmployee().getEmail());
        mrs.deleteEmail(req.getMail_info().getId());

    }

    public void generateFreeDays(Requests req) throws IOException, InvalidFormatException, MessagingException {
        FileInputStream fis = new FileInputStream("templates\\slobodnidenovi.docx");
        XWPFDocument doc = new XWPFDocument(OPCPackage.open(fis));
        EmployeeDto employeeDto = ers.getEmployeeByEmail(req.getMail_info().getSender_adress());
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();

            for (XWPFRun r : runs) {
                String text = r.getText(0);
                if (text != null) {
                    if (text.contains("position")) {
                        r.setBold(true);
                        r.setText(employeeDto.getDeptInfo().getDepartment_name().toLowerCase(), 0);
                    }
                    if (text.contains("numberOfDays")) {
                        r.setBold(true);
                        r.setText(req.getMail_info().getNumber_of_days(), 0);
                    }
                    if (text.contains("dateFrom")) {
                        r.setBold(true);
                        r.setText(req.getMail_info().getDate_from(), 0);
                    }
                    if (text.contains("dateTo")) {
                        r.setBold(true);
                        r.setText(req.getMail_info().getDate_to(), 0);
                    }
                }
            }
        }
        //DOLNIOT DEL OD DOKUMENTOT
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text.contains("documented")) {
                                String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                                r.setBold(true);
                                r.setText(date, 0);

                            }
                            if (text.contains("employee")) {
                                String rabotnik = employeeDto.getEmployee().getEmployee_name() + " " + employeeDto.getEmployee().getEmployee_surname();
                                r.setBold(true);
                                r.setText(rabotnik, 0);
                            }
                            if (text.contains("projectManager")) {
                                String projectManager = req.getManager_name() + " " + req.getManager_surmame();
                                r.setBold(true);
                                r.setText(projectManager, 0);
                            }
                        }
                    }
                }
            }
        }
        doc.write(new FileOutputStream("generatedPDFs\\slobodnidenoviNov.docx"));
        File inputWord = new File("generatedPDFs\\slobodnidenoviNov.docx");
        File outputFile = new File("generatedPDFs\\generated.pdf");
        InputStream docxInputStream = new FileInputStream(inputWord);
        OutputStream outputStream = new FileOutputStream(outputFile);
        IConverter converter = LocalConverter.builder().build();
        converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
        outputStream.close();
        byte[] array = Files.readAllBytes(Paths.get(String.valueOf(outputFile)));
        String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        frs.storePDF(array, employeeDto.getEmployee().getId(),date,req.getMail_info().getSubject_mail());
        ms.sendEmail("Вашето барање е прифатено од страна на тим менаџерот", employeeDto.getEmployee().getEmail());
        mrs.deleteEmail(req.getMail_info().getId());


    }


}
