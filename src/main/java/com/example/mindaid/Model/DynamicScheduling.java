package com.example.mindaid.Model;

import javax.persistence.*;

@Entity
@Table(name = "dynamic_scheduling")
public class DynamicScheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ds_id")
    public int dsId;
    @Column(name = "schedule_id")
    public int scheduleId;
    @Column(name = "day1")
    public String day1;
    @Column(name = "day2")
    public String day2;
    @Column(name = "day3")
    public String day3;

    public int getDsId() {
        return dsId;
    }

    public void setDsId(int dsId) {
        this.dsId = dsId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }
}
