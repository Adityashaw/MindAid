package com.example.mindaid.Dto;

public class ScheduleDto {
    public String scheduleDate;
    public String scheduleTime;
    public String scheduleDocName;
    public String scheduleMedia;
    public int scheduleDuration;
    public int paymentId;


    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleDocName() {
        return scheduleDocName;
    }

    public void setScheduleDocName(String scheduleDocName) {
        this.scheduleDocName = scheduleDocName;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getScheduleMedia() {
        return scheduleMedia;
    }

    public void setScheduleMedia(String scheduleMedia) {
        this.scheduleMedia = scheduleMedia;
    }

    public int getScheduleDuration() {
        return scheduleDuration;
    }

    public void setScheduleDuration(int scheduleDuration) {
        this.scheduleDuration = scheduleDuration;
    }
}
