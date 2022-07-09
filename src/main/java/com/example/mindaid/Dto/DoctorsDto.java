package com.example.mindaid.Dto;

import com.example.mindaid.Model.Doctors;

import java.util.ArrayList;
import java.util.List;

public class DoctorsDto {
    public String doc_id;
    List<Doctors> DoctorsList=new ArrayList<>();

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public List<Doctors> getDoctorsList() {
        return DoctorsList;
    }

    public void setDoctorsList(List<Doctors> doctorsList) {
        DoctorsList = doctorsList;
    }
}
