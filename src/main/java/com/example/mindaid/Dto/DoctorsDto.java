package com.example.mindaid.Dto;

import com.example.mindaid.Model.Doctors;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class DoctorsDto {
    public int doc_id;
    public String name;
    public String description;
    public String speciality;
    public String schedule;
    public String contactMedia;
    public int fee;
    public String education;
    List<Doctors> DoctorsList=new ArrayList<>();

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getContactMedia() {
        return contactMedia;
    }

    public void setContactMedia(String contactMedia) {
        this.contactMedia = contactMedia;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<Doctors> getDoctorsList() {
        return DoctorsList;
    }

    public void setDoctorsList(List<Doctors> doctorsList) {
        DoctorsList = doctorsList;
    }
}
