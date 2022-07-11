package com.example.mindaid.Model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name="doctors")
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="doc_id" )
    public int doc_id;
    @Column(name = "name")
    public String name;
    @Column(name="description")
    public String description;
    @Column(name="speciality")
    public String speciality;
    @Column(name="schedule")
    public String schedule;
    @Column(name="contact_media")
    public String contactMedia;
    @Column(name="education")
    public String education;

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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
