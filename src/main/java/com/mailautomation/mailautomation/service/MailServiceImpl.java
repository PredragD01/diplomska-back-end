package com.mailautomation.mailautomation.service;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.entities.KeyWords;
import com.mailautomation.mailautomation.entities.Mails;
import com.mailautomation.mailautomation.repositories.EmployeeRepositoryService;
import com.mailautomation.mailautomation.repositories.MailRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailRepositoryService mrs;

    @Autowired
    private EmployeeRepositoryService ers;

    private String[] emailsToIgnore = {"no-reply@accounts.google.com"};

    private Properties props = System.getProperties();

    private String SUCCESSFULL_SEND = "Вашето барање е прегледано од HR";

    private String ERROR_SEND = "Во Вашето барање има грешка. Ве молиме датумот да биде во фомрат dd.MM.yyyy (01.01.1970) и да го внесете бројот на денови." +
            " Датумите и бројот на денови да бидат одделени со празно место од другите зборови.";

    private String ERROR_SEND_SUBJECT = "Ве молиме внесете наслов на пораката";

    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}.\\d{2}.\\d{4}$");
    private static Pattern NUMBER_DAYS = Pattern.compile("^\\d{2}$");
    private static Pattern SINGLE_NUMBER_DAYS = Pattern.compile("^\\d{1}$");

    @Override
    public List<Mails> getMailsForUser(EmployeeDto employeeDto) throws MessagingException, IOException {
        HashMap<String,String> mapa = new HashMap<>();
        List<KeyWords> keyWords = mrs.getKeys();
        String subject_final = null;
        if(employeeDto.getDeptInfo().getId() == 1){
            Message messages[] = authenticateUser();
            for (int i = 0, n = messages.length; i < n; i++) {
                System.out.println("DURI SEA SUM TUKA");
                Message message = messages[i];
                if(!message.isSet(Flags.Flag.SEEN)){
                    String sender = message.getFrom()[0].toString().split("<")[1];
                    sender = sender.replace(">", "");
                    if(message.getSubject() != null && !message.getSubject().equals("")){
                        for(KeyWords key : keyWords){
                            mapa.put(key.getKey_word(), key.getValue_word());
                        }
                        for(String j : emailsToIgnore){
                            if(!j.equals(sender)){
                                ArrayList<String> ar = new ArrayList<String>();
                                String body = getTextFromMimeMultipart((MimeMultipart)message.getContent());
                                String[] bodyMail = body.split("\\s+");
                                for(String words : bodyMail) { //pronajdi datum i br denovi
//                                    words = words.replaceAll("[a-zA-Z]", "").replaceAll("-", "");
                                    String subject = message.getSubject();
                                    System.out.println(words + "-");
                                    if(mapa.containsKey(subject)){
                                        subject_final = mapa.get(subject);
                                    }else{
                                        subject_final = subject;
                                    }
                                    if (DATE_PATTERN.matcher(words).matches()) {
                                        ar.add(words);
                                    }
                                    if (NUMBER_DAYS.matcher(words).matches() || SINGLE_NUMBER_DAYS.matcher(words).matches()) {
                                        ar.add(words);
                                    }
                                }
                                System.out.println(ar);
                                if(ar.size() == 3){
                                    Mails mails = new Mails();
                                    mails.setDate_from(getDate(ar));
                                    mails.setDate_to(getDate(ar));
                                    mails.setNumber_of_days(getNumberOfDays(ar));
                                    mails.setAt_employee(employeeDto.getEmployee().getId());
                                    mails.setSender_adress(sender);
                                    mails.setBody_mail(body);
                                    mails.setStatus(0);
                                    mails.setSubject_mail(subject_final);
                                    mails.setDate_sent(message.getSentDate().toString());
                                    mrs.saveMails(mails);
                                    sendEmail(SUCCESSFULL_SEND, sender);
                                }else{
                                    sendEmail(ERROR_SEND, sender);
                                }
                            }
                        }
                    }else{
                        sendEmail(ERROR_SEND_SUBJECT, sender);

                    }
                }
            }

        }

        return  mrs.getMailsForUser(employeeDto);
    }

    public String getDate(ArrayList<String> arrayList ){
        String date = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if(DATE_PATTERN.matcher(arrayList.get(i)).matches()){
                date = arrayList.get(i);
                arrayList.remove(i);
                break;
            }
        }
        return date;
    }

    public String getNumberOfDays(ArrayList<String> arrayList ){
        String days = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if (NUMBER_DAYS.matcher(arrayList.get(i)).matches() || SINGLE_NUMBER_DAYS.matcher(arrayList.get(i)).matches()) {
                days = arrayList.get(i);
                arrayList.remove(i);
                break;
            }
        }
        return days;
    }


    @Override
    public void resendToManager(Mails email) {
        EmployeeDto employee = ers.getEmployeeByEmail(email.getSender_adress());
        int id = employee.getEmployee().getId();
        int manager = ers.getManagerOfEmployee(id);
        email.setAt_employee(manager);
        try {
            sendEmail("Вашето барање е препратено до Вашиот тим менаџер", email.getSender_adress());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mrs.editMails(email);
    }



    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }

    public Message[] authenticateUser() throws MessagingException {
        props.setProperty("mail.store.protocol", "imaps");
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", "diplomskabot@gmail.com", "pedzo123-");
        Folder inbox = store.getFolder("inbox");
        inbox.open(Folder.READ_WRITE);
        Message messages[] = inbox.getMessages();
        ///////

        return messages;
    }

    @Override
    public void declineMail(Mails mail) throws MessagingException {
        mail.setAt_employee(ers.getHR());
        mrs.deleteEmail(mail.getId());
        sendEmail("Вашето барање е одбиено од страна на Вашиот тим менаџер.", mail.getSender_adress());
    }

    @Override
    public void undefinedMail(int id, String to) {
        try {
            sendEmail("Вашето барање не е доволно јасно, Ве молиме пратете го барањето повторно", to);
            mrs.deleteEmail(id);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setKeyWords(KeyWords keyWords) {
        mrs.setKeyWords(keyWords);
    }

    @Override
    public void sendEmail(String messageText, String to) throws MessagingException {
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", "diplomskabot@gmail.com");
        props.put("mail.smtp.password", "pedzo123-");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("diplomskabot@gmail.com", "pedzo123-");
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
        message.setSubject("AВТОМАТИЗИРАНА ПОРАКА -- НЕ ВРАЌАЈТЕ НА ОВАА ПОРАКА --");
        message.setText(messageText);
        Transport.send(message);
    }
}
