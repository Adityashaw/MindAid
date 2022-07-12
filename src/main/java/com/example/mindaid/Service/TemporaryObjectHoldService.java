package com.example.mindaid.Service;

import com.example.mindaid.Dto.DoctorsDto;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class TemporaryObjectHoldService {
    public List<List<DoctorsDto>>doctorsDtoList=new ArrayList<>();

    public List<List<DoctorsDto>> getDoctorsDtoList() {
        return doctorsDtoList;
    }

    public void setDoctorsDtoList(List<List<DoctorsDto>> doctorsDtoList) {
        this.doctorsDtoList = doctorsDtoList;
    }
    public List<TemporaryObjectHoldService>temporaryObjectHoldServiceList=new ArrayList<>();
    public String scheduleTimeStr;
    public LocalTime scheduleTime;

    public List<TemporaryObjectHoldService> getTemporaryObjectHoldServiceList() {
        return temporaryObjectHoldServiceList;
    }

    public void setTemporaryObjectHoldServiceList(List<TemporaryObjectHoldService> temporaryObjectHoldServiceList) {
        this.temporaryObjectHoldServiceList = temporaryObjectHoldServiceList;
    }

    public String getScheduleTimeStr() {
        return scheduleTimeStr;
    }

    public void setScheduleTimeStr(String scheduleTimeStr) {
        this.scheduleTimeStr = scheduleTimeStr;
    }

    public LocalTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}
