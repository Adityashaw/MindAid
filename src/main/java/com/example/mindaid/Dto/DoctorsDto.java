package com.example.mindaid.Dto;

import com.example.mindaid.Model.Doctors;

import java.util.ArrayList;
import java.util.List;

public class DoctorsDto {
    public List<Doctors> getDoctorsList() {
        return DoctorsList;
    }

    public void setDoctorsList(List<Doctors> doctorsList) {
        DoctorsList = doctorsList;
    }

    List<Doctors> DoctorsList=new ArrayList<>();
}
