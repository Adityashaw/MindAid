package com.example.mindaid.Service;

import com.example.mindaid.Dto.DoctorsScheduleDto;
import com.example.mindaid.Model.DoctorConcern;
import com.example.mindaid.Model.Schedule;
import com.example.mindaid.Repository.DoctorConcernRepository;
import com.example.mindaid.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class DoctorsSchedulingService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    DoctorConcernRepository doctorConcernRepository;
    @Autowired
    DoctorsSchedulingService doctorsSchedulingService;
    public void updateSchedule(DoctorsScheduleDto doctorsScheduleDto) {
        doctorsSchedulingService.concernUpdater(doctorsScheduleDto);
        doctorsSchedulingService.scheduleUpdater(doctorsScheduleDto,doctorsScheduleDto.selectedSlotMessage,"message");
        doctorsSchedulingService.scheduleUpdater(doctorsScheduleDto,doctorsScheduleDto.selectedSlotLive,"live");

    }
    public void scheduleUpdater(DoctorsScheduleDto doctorsScheduleDto,List<String>schedules,String type){
        List<Schedule>existingPendings=scheduleRepository.findByDocIdAndApprovalAndMedia(doctorsScheduleDto.getDocId(),"pending",type);
        for (Schedule schedule:existingPendings){
            scheduleRepository.delete(schedule);
        }
        List<List<String>>days=new ArrayList();
        List<String>sunday=new ArrayList<>();
        sunday.add("0");
        sunday.add("Sunday");
        List<String>monday=new ArrayList<>();
        monday.add("1");
        monday.add("Monday");
        List<String>wednesday=new ArrayList<>();
        wednesday.add("3");
        wednesday.add("Wednesday");
        List<String>tuesday=new ArrayList<>();
        tuesday.add("2");
        tuesday.add("Tuesday");
        List<String>thursday=new ArrayList<>();
        thursday.add("4");
        thursday.add("Thursday");
        List<String>friday=new ArrayList<>();
        friday.add("5");
        friday.add("Friday");
        List<String>saturday=new ArrayList<>();
        saturday.add("6");
        for(String slot: schedules){
            String[] splittedSlot=slot.split(",");
            if(splittedSlot.length>1){
                if (splittedSlot[0].equals("0")){
                    sunday.add(splittedSlot[1]);
                }
                if (splittedSlot[0].equals("1")){
                    monday.add(splittedSlot[1]);
                }
                if (splittedSlot[0].equals("2")){
                    tuesday.add(splittedSlot[1]);
                }
                if (splittedSlot[0].equals("3")){
                    wednesday.add(splittedSlot[1]);
                }
                if (splittedSlot[0].equals("4")){
                    thursday.add(splittedSlot[1]);
                }
                if (splittedSlot[0].equals("5")){
                    friday.add(splittedSlot[1]);
                }
                if (splittedSlot[0].equals("6")){
                    saturday.add(splittedSlot[1]);
                }

            }
        }
        Collections.addAll(days,sunday,monday,tuesday,wednesday,thursday,friday,saturday);
        for (List<String>day:days){
            if (day.size()>2){
                Schedule schedule1=new Schedule();
                String times=day.get(2);
                for (int i=3;i<day.size();i++){
                    times=times+","+day.get(i);
                }
                schedule1.setScheduleDay(day.get(1));
                schedule1.setDoc_id(doctorsScheduleDto.getDocId());
                schedule1.setContactMedia(type);
                schedule1.setScheduleTimeStart(times);
                schedule1.setScheduleday_parameter(day.get(0));
                schedule1.setFee(Integer.parseInt(doctorsScheduleDto.getFeeMessage()));
                schedule1.setApproval("pending");
                scheduleRepository.save(schedule1);
            }
        }

    }
    public void concernUpdater (DoctorsScheduleDto doctorsScheduleDto){
        List<DoctorConcern>existingDocConcerns=doctorConcernRepository.findByDocIdAndApproval(doctorsScheduleDto.getDocId(),"pending");
        for (DoctorConcern doctorConcern:existingDocConcerns){
            doctorConcernRepository.delete(doctorConcern);
        }
        for(int i=0;i<doctorsScheduleDto.concerns.length;i++){
            DoctorConcern d=new DoctorConcern();
            if(doctorsScheduleDto.concerns[i]!=0){
                d.setConcern_id(i);
                d.setDoc_id(doctorsScheduleDto.getDocId());
                d.setDocconcern_id(1050);
                d.setApproval("pending");
                doctorConcernRepository.save(d);


            }
        }
    }
}
