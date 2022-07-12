package com.example.mindaid.Service;

import com.example.mindaid.Dto.DoctorsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SchedulingService {

    @Autowired
    SchedulingService schedulingService;
    public String AmPmFormetter(String strr1){
        String scheduleTimestr1;
        String am_pmStr=String.valueOf(strr1.charAt(0))+String.valueOf(strr1.charAt(1));
        int am_pmInt=Integer.parseInt(am_pmStr);
        if (am_pmInt>12){
            am_pmInt=am_pmInt-12;
            scheduleTimestr1=am_pmInt+String.valueOf(strr1.charAt(2))+String.valueOf(strr1.charAt(3))+String.valueOf(strr1.charAt(4)) +" pm";
        }
        else {
            scheduleTimestr1=am_pmInt+String.valueOf(strr1.charAt(2))+String.valueOf(strr1.charAt(3))+String.valueOf(strr1.charAt(4)) +" am";
        }
        return scheduleTimestr1;
    }

    public List<TemporaryObjectHoldService> getScheduleTimeAndTimeStr(DoctorsDto docDto, List<DoctorsDto> doctorsDtoList, Model model){
        List<TemporaryObjectHoldService>scheduleTimeAndTimeStr=new ArrayList<>();
        for (DoctorsDto doctorsDto : doctorsDtoList){
            if (docDto.doc_id==doctorsDto.doc_id){
                Time time=doctorsDto.scheduleTimeStart;
                LocalTime localTime=time.toLocalTime();
                TemporaryObjectHoldService obj1=new TemporaryObjectHoldService();
                TemporaryObjectHoldService obj2=new TemporaryObjectHoldService();
                TemporaryObjectHoldService obj3=new TemporaryObjectHoldService();
                obj1.setScheduleTime(localTime);
                obj2.setScheduleTime(localTime.plusHours(1));
                obj3.setScheduleTime(localTime.plusHours(2));
                String strr1=localTime.toString();
                String strr2=(localTime.plusHours(1)).toString();
                String strr3=(localTime.plusHours(2)).toString();
                String scheduleTimestr1=schedulingService.AmPmFormetter(strr1);
                String scheduleTimestr2=schedulingService.AmPmFormetter(strr2);
                String scheduleTimestr3=schedulingService.AmPmFormetter(strr3);
                obj1.setScheduleTimeStr(scheduleTimestr1);
                obj2.setScheduleTimeStr(scheduleTimestr2);
                obj3.setScheduleTimeStr(scheduleTimestr3);
                Collections.addAll(scheduleTimeAndTimeStr,obj1,obj2,obj3);
                model.addAttribute(doctorsDto);
                return scheduleTimeAndTimeStr;
            }
        }
        return scheduleTimeAndTimeStr;
    }
}
