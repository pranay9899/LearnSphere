package com.learnsphere.service;

import com.learnsphere.entity.Qualification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QualificationService {
    public  void addQualification(Qualification qualification);
    public  void updateQualification(Qualification qualification);
    public  void deleteQualification(int id);
    public  Qualification getQualification(int id);
    public List<Qualification> getQualifications();
    public List<Qualification> getQualificationsByName(String name);
}
