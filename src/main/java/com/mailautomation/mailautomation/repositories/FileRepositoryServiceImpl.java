package com.mailautomation.mailautomation.repositories;

import com.mailautomation.mailautomation.entities.Documents;
import com.mailautomation.mailautomation.entities.Mails;
import org.apache.poi.hpsf.Blob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class FileRepositoryServiceImpl implements FileRepositoryService {
    @Autowired
    private EntityManager em;

    @Transactional
    @Override
    public void storePDF(byte[] pdf,int id,String date, String title) {
        Documents doc = new Documents();
        doc.setDocument_path(pdf);
        doc.setOwner_doc(id);
        doc.setDate_created(date);
        doc.setTitle(title);
        em.persist(doc);
    }

    @Override
    public byte[] getPDF(int id) {
        Documents doc = em.find(Documents.class, id);
        return doc.getDocument_path();
    }

    @Override
    public List<Documents> getAllDocuments() {
        Query q = em.createNativeQuery("select * from documents", Documents.class);
        return q.getResultList();
    }
}
