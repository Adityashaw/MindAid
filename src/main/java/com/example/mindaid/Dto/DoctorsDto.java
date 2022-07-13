package com.example.mindaid.Dto;

import com.example.mindaid.Model.Doctors;

import javax.persistence.Column;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorsDto {
    public int doc_id;
    public String name;
    public String description;
    public String speciality;
    public String scheduleTime;
    public String contactMedia;
    public String education;

    public String scheduleDay;
    public Time scheduleTimeStart;
    public int fee;
    List<Doctors> DoctorsList=new ArrayList<>();

    public Date selectedScheduleDay;
    public LocalTime selectedScheduleTime;
    public String scheduleday_parameter;

    public String getScheduleday_parameter() {
        return scheduleday_parameter;
    }

    public void setScheduleday_parameter(String scheduleday_parameter) {
        this.scheduleday_parameter = scheduleday_parameter;
    }

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

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getContactMedia() {
        return contactMedia;
    }

    public void setContactMedia(String contactMedia) {
        this.contactMedia = contactMedia;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public Time getScheduleTimeStart() {
        return scheduleTimeStart;
    }

    public void setScheduleTimeStart(Time scheduleTimeStart) {
        this.scheduleTimeStart = scheduleTimeStart;
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

    public Date getSelectedScheduleDay() {
        return selectedScheduleDay;
    }

    public void setSelectedScheduleDay(Date selectedScheduleDay) {
        this.selectedScheduleDay = selectedScheduleDay;
    }

    public LocalTime getSelectedScheduleTime() {
        return selectedScheduleTime;
    }

    public void setSelectedScheduleTime(LocalTime selectedScheduleTime) {
        this.selectedScheduleTime = selectedScheduleTime;
    }
}
