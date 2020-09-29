package com.mailautomation.mailautomation.repositories;

import com.mailautomation.mailautomation.dtos.EmployeeDto;
import com.mailautomation.mailautomation.entities.KeyWords;
import com.mailautomation.mailautomation.entities.Mails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class MailRepositoryServiceImpl implements MailRepositoryService {

    @Autowired
    private EntityManager em;

    @Transactional
    @Override
    public List<Mails> getMailsForUser(EmployeeDto employeeDto) {
        if(employeeDto.getDeptInfo().getId() == 4 || employeeDto.getDeptInfo().getId() == 1){ //ako e PM ili HR
            Query q = em.createNativeQuery("select * from mails WHERE at_employee = ? and status_ = 0", Mails.class)
                    .setParameter(1, employeeDto.getEmployee().getId());
            return q.getResultList();
        }else{
            Query q = em.createNativeQuery("select * from mails WHERE sender_adress = ?", Mails.class)
                    .setParameter(1, employeeDto.getEmployee().getEmail());
            return q.getResultList();
        }

    }

    @Transactional
    @Override
    public void saveMails(Mails mails) {
            em.persist(mails);
    }

    @Transactional
    @Override
    public void editMails(Mails mails) {
        em.merge(mails);
    }

    @Transactional
    @Override
    public List<KeyWords> getKeys() {
        Query q = em.createNativeQuery("SELECT * FROM key_words", KeyWords.class);
        return q.getResultList();
    }

    @Transactional
    @Override
    public void deleteEmail(int id) {
        Mails mail = em.find(Mails.class, id);
        em.remove(mail);
    }

    @Transactional
    @Override
    public void setKeyWords(KeyWords keyWords) {
        em.persist(keyWords);
    }
}
