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
    public String education;
    public String experience;
    public String email;
    public String mobile;
    public String age;
    public String gender;
    public String appliedDate;
    public String scheduleTime;
    public String contactMedia;
    public int scheduleId;
    public String scheduleDay;
    public String scheduleTimeStart;
    public int fee;
    public String selectedScheduleDay;
    public String selectedScheduleTime;
    public String scheduleday_parameter;
    public String photos;

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    List<Doctors> DoctorsList=new ArrayList<>();

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }

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
