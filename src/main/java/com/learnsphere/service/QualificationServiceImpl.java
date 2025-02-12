package com.learnsphere.service;

import com.learnsphere.entity.Qualification;
import com.learnsphere.repository.QualificationRepository;

import java.util.List;

public class QualificationServiceImpl implements QualificationService {

    QualificationRepository qualificationRepository;

    public QualificationServiceImpl(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    @Override
    public void addQualification(Qualification qualification) {
        qualificationRepository.save(qualification);
    }

    @Override
    public void updateQualification(Qualification qualification) {
        qualificationRepository.save(qualification);
    }

    @Override
    public void deleteQualification(int id) {
        qualificationRepository.deleteById(id);
    }

    @Override
    public Qualification getQualification(int id) {
        return qualificationRepository.getById(id);
    }

    @Override
    public List<Qualification> getQualifications() {
        return qualificationRepository.findAll();
    }

    @Override
    public List<Qualification> getQualificationsByName(String name) {
        return qualificationRepository.findByQualification(name);
    }
}
