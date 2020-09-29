package com.mailautomation.mailautomation.repositories;

import com.mailautomation.mailautomation.entities.Documents;

import java.util.List;

public interface FileRepositoryService {
    void storePDF(byte[] pdf, int id,String date,String title);
    byte[] getPDF(int id);
    List<Documents> getAllDocuments();
}
