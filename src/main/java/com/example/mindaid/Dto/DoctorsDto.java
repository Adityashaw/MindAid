package com.example.mindaid.Dto;

import com.example.mindaid.Model.Doctors;

import javax.persistence.Column;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorsDto {
    public int docId;
    public String name;
    public String description;
    public String speciality;
    public String scheduleTime;
    public String contactMedia;
    public String education;
    public int scheduleId;
    public String scheduleDay;
    public String scheduleTimeStart;
    public int fee;
    public String selectedScheduleDay;
    public String selectedScheduleTime;
    public String scheduleday_parameter;
    List<Doctors> DoctorsList=new ArrayList<>();

    public String getScheduleday_parameter() {
        return scheduleday_parameter;
    }

    public void setScheduleday_parameter(String scheduleday_parameter) {
        this.scheduleday_parameter = scheduleday_parameter;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
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

    public String getScheduleTimeStart() {
        return scheduleTimeStart;
    }

    public void setScheduleTimeStart(String scheduleTimeStart) {
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

    public String getSelectedScheduleDay() {
        return selectedScheduleDay;
    }

    public void setSelectedScheduleDay(String selectedScheduleDay) {
        this.selectedScheduleDay = selectedScheduleDay;
    }

    public String getSelectedScheduleTime() {
        return selectedScheduleTime;
    }

    public void setSelectedScheduleTime(String selectedScheduleTime) {
        this.selectedScheduleTime = selectedScheduleTime;
    }
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
