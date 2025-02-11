package com.learnsphere.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String qualification;
    private String major;
    private String description;
    private float CGPA;
    private float percentage;
    private String status;
    @Temporal(TemporalType.DATE)
    private Date graduationDate;

    public Qualification() {
    }

    public Qualification(Long id, String qualification, String major, String description, float CGPA, float percentage, String status, Date graduationDate) {
        this.id = id;
        this.qualification = qualification;
        this.major = major;
        this.description = description;
        this.CGPA = CGPA;
        this.percentage = percentage;
        this.status = status;
        this.graduationDate = graduationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCGPA() {
        return CGPA;
    }

    public void setCGPA(float CGPA) {
        this.CGPA = CGPA;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    @Override
    public String toString() {
        return "Qualification{" +
                "id=" + id +
                ", qualification='" + qualification + '\'' +
                ", major='" + major + '\'' +
                ", description='" + description + '\'' +
                ", CGPA=" + CGPA +
                ", percentage=" + percentage +
                ", status='" + status + '\'' +
                ", graduationDate=" + graduationDate +
                '}';
    }
}
