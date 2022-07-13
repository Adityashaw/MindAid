package com.example.mindaid.Service;

import com.example.mindaid.Dto.DoctorsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.DatabaseMetaData;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
                getScheduleDays(doctorsDto,model);
                if (doctorsDto.contactMedia.equals("live")) {
                    Time time = doctorsDto.scheduleTimeStart;
                    LocalTime localTime = time.toLocalTime();
                    TemporaryObjectHoldService obj1 = new TemporaryObjectHoldService();
                    TemporaryObjectHoldService obj2 = new TemporaryObjectHoldService();
                    TemporaryObjectHoldService obj3 = new TemporaryObjectHoldService();
                    obj1.setScheduleTime(localTime);
                    obj2.setScheduleTime(localTime.plusHours(1));
                    obj3.setScheduleTime(localTime.plusHours(2));
                    String strr1 = localTime.toString();
                    String strr2 = (localTime.plusHours(1)).toString();
                    String strr3 = (localTime.plusHours(2)).toString();
                    String scheduleTimestr1 = schedulingService.AmPmFormetter(strr1);
                    String scheduleTimestr2 = schedulingService.AmPmFormetter(strr2);
                    String scheduleTimestr3 = schedulingService.AmPmFormetter(strr3);
                    obj1.setScheduleTimeStr(scheduleTimestr1);
                    obj2.setScheduleTimeStr(scheduleTimestr2);
                    obj3.setScheduleTimeStr(scheduleTimestr3);
                    Collections.addAll(scheduleTimeAndTimeStr, obj1, obj2, obj3);
                    doctorsDto.setScheduleTime(scheduleTimestr1+"-"+scheduleTimestr3);
                    model.addAttribute("doctorsDto",doctorsDto);

                    return scheduleTimeAndTimeStr;
                }
                else {
                    Time time = doctorsDto.scheduleTimeStart;
                    LocalTime localTime = time.toLocalTime();
                    TemporaryObjectHoldService obj1 = new TemporaryObjectHoldService();
                    TemporaryObjectHoldService obj2 = new TemporaryObjectHoldService();
                    obj1.setScheduleTime(localTime);
                    obj2.setScheduleTime(localTime.plusHours(3));
                    String strr1 = localTime.toString();
                    String strr2 = (localTime.plusHours(3)).toString();
                    String scheduleTimestr1 = schedulingService.AmPmFormetter(strr1);
                    String scheduleTimestr2 = schedulingService.AmPmFormetter(strr2);
                    obj1.setScheduleTimeStr(scheduleTimestr1);
                    obj2.setScheduleTimeStr(scheduleTimestr2);
                    Collections.addAll(scheduleTimeAndTimeStr, obj1, obj2);
                    doctorsDto.setScheduleTime(scheduleTimestr1+"-"+scheduleTimestr2);
                    model.addAttribute("doctorsDto",doctorsDto);
                    return scheduleTimeAndTimeStr;
                }
            }
        }
        return scheduleTimeAndTimeStr;
    }

    public void getScheduleDays(DoctorsDto doctorsDto,Model model){
        List<TemporaryObjectHoldService> scheduleDays=new ArrayList<>();

        TemporaryObjectHoldService obj1=new TemporaryObjectHoldService();
        TemporaryObjectHoldService obj2=new TemporaryObjectHoldService();
        TemporaryObjectHoldService obj3=new TemporaryObjectHoldService();

        LocalDate date1=LocalDate.now().plusDays(1);
        obj1.setScheduleDate(date1);
        obj1.setActiveStatus(getActiveStatus(doctorsDto,date1));
        obj1.setActiveStatusBool(getActiveStatusBool(doctorsDto,date1));

        LocalDate date2= LocalDate.now().plusDays(2);
        obj2.setScheduleDate(date2);
        obj2.setActiveStatus(getActiveStatus(doctorsDto,date2));
        obj2.setActiveStatusBool(getActiveStatusBool(doctorsDto,date2));

        LocalDate date3= LocalDate.now().plusDays(3);
        obj3.setScheduleDate(date3);
        obj3.setActiveStatus(getActiveStatus(doctorsDto,date3));
        obj3.setActiveStatusBool(getActiveStatusBool(doctorsDto,date3));

       Collections.addAll(scheduleDays,obj1,obj2,obj3);
       model.addAttribute("scheduleDays",scheduleDays);
    }

    public int getActiveStatus(DoctorsDto doctorsDto,LocalDate date){
        int status=0;
        int day= date.getDayOfWeek().getValue();
        char[] scheduleday_paramater=doctorsDto.getScheduleday_parameter().toCharArray();
        for(char c: scheduleday_paramater){
            if(day==Character.getNumericValue(c)){
                status=1;
            }
        }
        return status;
    }

    public boolean getActiveStatusBool(DoctorsDto doctorsDto,LocalDate date){
        boolean status=true;
        int day= date.getDayOfWeek().getValue();
        char[] scheduleday_paramater=doctorsDto.getScheduleday_parameter().toCharArray();
        for(char c: scheduleday_paramater){
            if(day==Character.getNumericValue(c)){
                status=false;
            }
        }
        return status;
    }
}