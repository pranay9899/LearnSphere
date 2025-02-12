package com.learnsphere.repository;

import com.learnsphere.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification,Integer> {
    List<Qualification> findByQualification(String qualification);
}
