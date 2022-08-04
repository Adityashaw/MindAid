package com.example.mindaid.Model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    public int scheduleId;
    @Column(name = "doc_id")
    public int doc_id;
    @Column(name = "contact_media")
    public String contactMedia;
    @Column(name = "schedule_day")
    public String scheduleDay;
    @Column(name = "schedule_time_start")
    public String scheduleTimeStart;
    @Column(name = "fee")
    public int fee;
    @Column(name = "scheduleday_parameter")
    public String scheduleday_parameter;
    @Column(name = "approval")
    public String approval;

    public String getScheduleday_parameter() {
        return scheduleday_parameter;
    }

    public void setScheduleday_parameter(String scheduleday_parameter) {
        this.scheduleday_parameter = scheduleday_parameter;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
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

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }
}
