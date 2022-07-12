package com.example.mindaid.Service;

import org.springframework.stereotype.Service;

@Service
public class SchedulingService {
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
}
