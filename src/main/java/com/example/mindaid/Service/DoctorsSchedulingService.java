package com.example.mindaid.Service;

import com.example.mindaid.Dto.DoctorsScheduleDto;
import com.example.mindaid.Model.Concern;
import com.example.mindaid.Model.DoctorConcern;
import com.example.mindaid.Model.Schedule;
import com.example.mindaid.Repository.ConcernRepository;
import com.example.mindaid.Repository.DoctorConcernRepository;
import com.example.mindaid.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    @Autowired
    TemporaryObjectHoldService temporaryObjectHoldService;
    @Autowired
    ConcernRepository concernRepository;
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
        sunday.add("7");
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
                if (splittedSlot[0].equals("7")){
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
        String UltimateDays="";
        String UltimateTimes="";
        String UltimateScheduleDayParam="";

        for (List<String>day:days){
            if (day.size()>2){
                if (UltimateDays.equals("")){
                    UltimateDays=day.get(1);
                }
                else {
                    UltimateDays = UltimateDays + "," + day.get(1);
                }
                UltimateScheduleDayParam=UltimateScheduleDayParam+day.get(0);
                String times=day.get(0)+"~"+day.get(2);
                for (int i=3;i<day.size();i++){
                    times=times+","+day.get(0)+"~"+day.get(i);
                }
                if (UltimateTimes.equals("")){
                    UltimateTimes=times;
                }
                else {
                    UltimateTimes=UltimateTimes+","+times;
                }

            }
        }
        Schedule schedule=new Schedule();
        schedule.setApproval("pending");
        schedule.setScheduleDay(UltimateDays);
        schedule.setDoc_id(doctorsScheduleDto.getDocId());
        schedule.setScheduleTimeStart(UltimateTimes);
        schedule.setScheduleday_parameter(UltimateScheduleDayParam);
        schedule.setContactMedia(type);
        if (type.equals("message")) {
            schedule.setFee(Integer.parseInt(doctorsScheduleDto.getFeeMessage()));
        }
        else {
            schedule.setFee(Integer.parseInt(doctorsScheduleDto.getFeeLive()));
        }
        if(schedule.getScheduleDay().equals("")){}
        else scheduleRepository.save(schedule);

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
    public String getSetDoctorsSchedule(Model model){
        DoctorsScheduleDto doctorsScheduleDto=new DoctorsScheduleDto();
        doctorsScheduleDto.setDocId(temporaryObjectHoldService.getUserDto().getUserId());
        List<DoctorsScheduleDto> doctorsScheduleDtoList=new ArrayList<>();
        List<Concern>concernList=concernRepository.findAll();
        for(int i=0;i<7;i++){
            DoctorsScheduleDto doctorsScheduleDto1=new DoctorsScheduleDto();
            doctorsScheduleDto1.setDayParameter(Integer.toString(i));
            if(i==0) doctorsScheduleDto1.setDay("Sunday");
            else if(i==1) doctorsScheduleDto1.setDay("Monday");
            else if(i==2) doctorsScheduleDto1.setDay("Tuesday");
            else if(i==3) doctorsScheduleDto1.setDay("Wednesday");
            else if(i==4) doctorsScheduleDto1.setDay("Thursday");
            else if(i==5) doctorsScheduleDto1.setDay("Friday");
            else doctorsScheduleDto1.setDay("Saturday");
            doctorsScheduleDtoList.add(doctorsScheduleDto1);
        }
        List<String> slotListMessage=new ArrayList<>();
        List<String> slotListLive=new ArrayList<>();
        for(int message=10;message<=19;message+=3){
            String m_time=message+":00:00";
            slotListMessage.add(m_time);
        }
        for(int live=10;live<=21;live++){
            String l_time=live+":00:00";
            slotListLive.add(l_time);
        }
        model.addAttribute("slotListMessage",slotListMessage);
        model.addAttribute("slotListLive",slotListLive);
        model.addAttribute("concernList",concernList);
        model.addAttribute("doctorsScheduleDtoList",doctorsScheduleDtoList);

        model.addAttribute(doctorsScheduleDto);


        //Display current Schedule
        List<Schedule>schedules=scheduleRepository.findByDocIdAndApproval(doctorsScheduleDto.getDocId(),"approved");
        List<Schedule> scheduleList=new ArrayList<>();
        for (Schedule schedule1:schedules){
            char[] scheduleDayParam=schedule1.getScheduleday_parameter().toCharArray();
            String[] scheduleDay=schedule1.getScheduleDay().split(",");
            for(int i=0;i<scheduleDay.length;i++) {
                char c=scheduleDayParam[i];
                Schedule schedule2=new Schedule();
                String scheduleTimestrts = "";

                String[] schedulesList = schedule1.getScheduleTimeStart().split(",");
                for (String st : schedulesList) {
                    String[] spliTedSchedule = st.split("~");
                    if (scheduleTimestrts.equals("") && Integer.parseInt(spliTedSchedule[0])==(Character.getNumericValue(c))) {
                        scheduleTimestrts = spliTedSchedule[1];
                    } else if(Integer.parseInt(spliTedSchedule[0])==(Character.getNumericValue(c))){
                        scheduleTimestrts = scheduleTimestrts + "," + spliTedSchedule[1];
                    }
                }
                schedule2.setScheduleTimeStart(scheduleTimestrts);
                schedule2.setScheduleDay(scheduleDay[i]);
                schedule2.setContactMedia(schedule1.getContactMedia());


                scheduleList.add(schedule2);


            }
        }
        if (scheduleList.size()>1){
            model.addAttribute("flag",1);
        }
        else {
            model.addAttribute("flag",0);
        }
        model.addAttribute("scheduleList",scheduleList);
        //Display end

        return "doctorSchedule";
    }
}
