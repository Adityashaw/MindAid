package com.example.mindaid.Service;

import com.example.mindaid.Dto.DoctorsDto;
import com.example.mindaid.Dto.PaymentDto;
import com.example.mindaid.Dto.ScheduleDto;
import com.example.mindaid.Model.DynamicScheduling;
import com.example.mindaid.Model.Payment;
import com.example.mindaid.Model.Schedule;
import com.example.mindaid.Repository.DoctorsRepository;
import com.example.mindaid.Repository.DynamicSchedulingrepository;
import com.example.mindaid.Repository.PaymentRepository;
import com.example.mindaid.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.DatabaseMetaData;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class SchedulingService {

    @Autowired
    SchedulingService schedulingService;
    @Autowired
    TemporaryObjectHoldService temporaryObjectHoldService;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    DynamicSchedulingrepository dynamicSchedulingrepository;

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
            if (docDto.docId==doctorsDto.docId){
                getScheduleDays(doctorsDto,model);
                if (doctorsDto.contactMedia.equals("live") || doctorsDto.contactMedia.equals("message")) {
//                    Time time = doctorsDto.scheduleTimeStart;
                    String [] scheduleTimeList=doctorsDto.scheduleTimeStart.split(",");
                    for (String scheduleTime:scheduleTimeList){
                        TemporaryObjectHoldService obj = new TemporaryObjectHoldService();
                        String scheduleTimestr = schedulingService.AmPmFormetter(scheduleTime.split("~")[1]);
                        obj.setScheduleTimeStr(scheduleTimestr);
                        scheduleTimeAndTimeStr.add(obj);
                    }
//                    LocalTime localTime = time.toLocalTime();
//                    TemporaryObjectHoldService obj1 = new TemporaryObjectHoldService();
//                    TemporaryObjectHoldService obj2 = new TemporaryObjectHoldService();
//                    TemporaryObjectHoldService obj3 = new TemporaryObjectHoldService();
//                    obj1.setScheduleTime(localTime);
//                    obj2.setScheduleTime(localTime.plusHours(1));
//                    obj3.setScheduleTime(localTime.plusHours(2));
//                    String strr1 = localTime.toString();
//                    String strr2 = (localTime.plusHours(1)).toString();
//                    String strr3 = (localTime.plusHours(2)).toString();
//                    String scheduleTimestr1 = schedulingService.AmPmFormetter(strr1);
//                    String scheduleTimestr2 = schedulingService.AmPmFormetter(strr2);
//                    String scheduleTimestr3 = schedulingService.AmPmFormetter(strr3);
//                    obj1.setScheduleTimeStr(scheduleTimestr1);
//                    obj2.setScheduleTimeStr(scheduleTimestr2);
//                    obj3.setScheduleTimeStr(scheduleTimestr3);
//                    Collections.addAll(scheduleTimeAndTimeStr, obj1, obj2, obj3);
//                    doctorsDto.setScheduleTime(schedulingService.AmPmFormetter(scheduleTimeList[0])+"-"+schedulingService.AmPmFormetter(scheduleTimeList[scheduleTimeList.length-1]));
                    doctorsDto.setScheduleTime("Not Set Yet");//upper line
                    model.addAttribute("doctorsDto",doctorsDto);
                    return scheduleTimeAndTimeStr;
                }
//                else {
//                    Time time = doctorsDto.scheduleTimeStart;
//                    LocalTime localTime = time.toLocalTime();
//                    TemporaryObjectHoldService obj1 = new TemporaryObjectHoldService();
//                    TemporaryObjectHoldService obj2 = new TemporaryObjectHoldService();
//                    obj1.setScheduleTime(localTime);
//                    obj2.setScheduleTime(localTime.plusHours(3));
//                    String strr1 = localTime.toString();
//                    String strr2 = (localTime.plusHours(3)).toString();
//                    String scheduleTimestr1 = schedulingService.AmPmFormetter(strr1);
//                    String scheduleTimestr2 = schedulingService.AmPmFormetter(strr2);
//                    obj1.setScheduleTimeStr(scheduleTimestr1);
//                    obj2.setScheduleTimeStr(scheduleTimestr2);
//                    Collections.addAll(scheduleTimeAndTimeStr, obj1, obj2);
//                    doctorsDto.setScheduleTime(scheduleTimestr1+"-"+scheduleTimestr2);
//                    model.addAttribute("doctorsDto",doctorsDto);
//                    return scheduleTimeAndTimeStr;
//                }
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
//        System.out.println("sunday paisi"+date1.getDayOfWeek().getValue());
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
       schedulingService.updateDynamicSchedule(doctorsDto,date1.toString(),date2.toString(),date3.toString());
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
        if(day==0){
            System.out.println("sunday paisi");
        }
        char[] scheduleday_paramater=doctorsDto.getScheduleday_parameter().toCharArray();
        for(char c: scheduleday_paramater){
            if(day==Character.getNumericValue(c)){
                if(day==1){
                    System.out.println("monday paisi");
                }


                status=false;
            }
        }
        return status;
    }

    public String randomURLGenerator(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;

    }

    public void savePaymentDetails(Payment paymentDto, DoctorsDto doctorsDto){
        paymentDto.setUserId(temporaryObjectHoldService.getUserDto().getUserId());
        paymentDto.setDocId((doctorsDto.getDocId()));
        paymentDto.setScheduleDate(doctorsDto.getSelectedScheduleDay());
        paymentDto.setScheduleTime(doctorsDto.getSelectedScheduleTime());
        paymentDto.setScheduleId(doctorsDto.getScheduleId());
        paymentDto.setContactMedia(doctorsDto.getContactMedia());
        paymentDto.setActiveStatus(2); //future 2
        paymentDto.setApproval("pending");
        String generatedString;
        if(doctorsDto.contactMedia.equals("live")){
            generatedString="https://meet.jit.si/mindaid"+schedulingService.randomURLGenerator();
            paymentDto.setScheduleDuration(1);
        }
        else{
            generatedString="http://localhost:9090/chat";
            paymentDto.setScheduleDuration(3);
        }
        paymentDto.setSessionLink(generatedString);
        paymentRepository.save(paymentDto);
    }

    public List<ScheduleDto> getcheduleInfo(Model model,int cStatus, String approval,String userType){
        List<Payment>paymentList=new ArrayList<>();
        if (userType.equals("user")) {
            paymentList = paymentRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        }
        else {
           paymentList = paymentRepository.findByDocId(temporaryObjectHoldService.userDto.userId);
        }
        List <ScheduleDto>scheduleInfoList=new ArrayList<>();
        for (Payment payment: paymentList){
            LocalDate localDate=LocalDate.now();
            LocalDate localDate1=LocalDate.parse(payment.getScheduleDate());
            String timeNow=schedulingService.AmPmFormetter(LocalTime.now().toString());
            String timeTaken=payment.scheduleTime;
            int timeNowMinInt=schedulingService.TimeStrToMinuteIntConverter(timeNow);
            int timeNowMinIntScheduled=schedulingService.TimeStrToMinuteIntConverter(timeTaken);
            if (localDate1.compareTo(localDate)>=0){//upcoming and present days
                if (localDate1.compareTo(localDate) ==0){ //present day
                    if (timeNowMinInt>=timeNowMinIntScheduled){//present day present time and pastime
                        if (timeNowMinInt-timeNowMinIntScheduled<payment.getScheduleDuration()*60){//present day and inside scheduled time
                            payment.setActiveStatus(1);
                        }
                        else {//present day pastime
                            payment.setActiveStatus(0);
                        }
                    }
                    else {//present day upcoming time
                        payment.setActiveStatus(2);
                    }
                }
                else {//upcoming day
                    payment.setActiveStatus(2);
                }
            }
            else {//past day
                payment.setActiveStatus(0);
            }
            paymentRepository.save(payment);
            if (payment.activeStatus==cStatus){
                ScheduleDto scheduleDto=new ScheduleDto();
                scheduleDto.setScheduleDate(payment.getScheduleDate());
                scheduleDto.setScheduleTime(payment.getScheduleTime());
                scheduleDto.setScheduleDocName((doctorsRepository.findByDocId(payment.getDocId())).get(0).getName());
                scheduleDto.setScheduleMedia((scheduleRepository.findByScheduleId(payment.getScheduleId())).get(0).getContactMedia());
                scheduleDto.setScheduleDuration(payment.getScheduleDuration());
                scheduleDto.setPaymentId(payment.getPaymentId());
                scheduleInfoList.add(scheduleDto);
            }
        }
        return  scheduleInfoList;
    }

    public int TimeStrToMinuteIntConverter(String timeStr){
        int minInt=0;
        String [] splitByAmPm=timeStr.split(" ");
        String [] splitByHrMin=splitByAmPm[0].split(":");
        if (splitByAmPm[1].equals("am")){
            minInt=(Integer.parseInt(splitByHrMin[0]))*60+(Integer.parseInt(splitByHrMin[1]));
        }
        else {
            minInt=(Integer.parseInt(splitByHrMin[0])+12)*60+(Integer.parseInt(splitByHrMin[1]));
        }
        return minInt;
    }

    public void updateDynamicSchedule(DoctorsDto doctorsDto, String date1,String date2,String date3){
        List<DynamicScheduling>dynamicSchedulingList=dynamicSchedulingrepository.findByScheduleId(doctorsDto.scheduleId);
        if (dynamicSchedulingList.isEmpty()){
            DynamicScheduling dynamicScheduling=new DynamicScheduling();
            dynamicScheduling.setScheduleId(doctorsDto.scheduleId);
            dynamicScheduling.setDay1(date1.toString()+",000");
            dynamicScheduling.setDay2(date2.toString()+",000");
            dynamicScheduling.setDay3(date3.toString()+",000");
            dynamicSchedulingrepository.save(dynamicScheduling);
        }
        else {
            String [] date1Parsing=dynamicSchedulingList.get(0).day1.split(",");
            String [] date2Parsing=dynamicSchedulingList.get(0).day2.split(",");
            String [] date3Parsing=dynamicSchedulingList.get(0).day3.split(",");

            if (date1.equals(date1Parsing[0]));
            else if (date1.equals(date2Parsing[1])){
                dynamicSchedulingList.get(0).setDay1(dynamicSchedulingList.get(0).day2);
                dynamicSchedulingList.get(0).setDay2(dynamicSchedulingList.get(0).day3);
                dynamicSchedulingList.get(0).setDay1(date3+",000");
            }
            else if (date1.equals(date3Parsing[0])){
                dynamicSchedulingList.get(0).setDay1(dynamicSchedulingList.get(0).day3);
                dynamicSchedulingList.get(0).setDay2(date2+",000");
                dynamicSchedulingList.get(0).setDay3(date3+",000");
            }
            else {
                dynamicSchedulingList.get(0).setDay1(date1+",000");
                dynamicSchedulingList.get(0).setDay2(date2+",000");
                dynamicSchedulingList.get(0).setDay3(date3+",000");
            }
            dynamicSchedulingrepository.save(dynamicSchedulingList.get(0));
        }
    }

    public void updateScheduleTimeAvailibility(DoctorsDto doctorsDto, Payment paymentDto){
        List<Schedule>scheduleList=scheduleRepository.findByScheduleId(paymentDto.getScheduleId());
        String[]scheduleTimeList=scheduleList.get(0).getScheduleTimeStart().split(",");
        int index=0;
        String date=paymentDto.getScheduleDate();
        LocalDate localDate=LocalDate.parse(date);
        int dayInt=localDate.getDayOfWeek().getValue();
        String timestart="";
        for (int i=0;i<scheduleTimeList.length;i++){
            String[]scheduleTimelistSplit=scheduleTimeList[i].split("~");
            if (dayInt==Integer.parseInt(scheduleTimelistSplit[0])){ //&& paymentDto.scheduleTime.equals(schedulingService.AmPmFormetter(scheduleTimelistSplit[1]))
                if (timestart.equals("")){
                    timestart=scheduleTimeList[i];
                }
                else {
                    timestart = timestart+","+scheduleTimeList[i];
                }
            }
        }
        String[]scheduleTimeList2=timestart.split(",");
        for (int i=0;i<scheduleTimeList2.length;i++){
            String[]scheduleTimelistSplit=scheduleTimeList[i].split("~");
            if (paymentDto.scheduleTime.equals(schedulingService.AmPmFormetter(scheduleTimelistSplit[1]))){
                index=i;
            }
        }
        List<DynamicScheduling>dynamicSchedulingList=dynamicSchedulingrepository.findByScheduleId(doctorsDto.getScheduleId());
        String [] date1Parsing=dynamicSchedulingList.get(0).day1.split(",");
        String [] date2Parsing=dynamicSchedulingList.get(0).day2.split(",");
        String [] date3Parsing=dynamicSchedulingList.get(0).day3.split(",");
        char ch='1';
        if (date1Parsing[0].equals(paymentDto.scheduleDate)){
            StringBuilder stringBuilder=new StringBuilder(date1Parsing[1]);
            stringBuilder.setCharAt(index, ch);
            String day=date1Parsing[0]+","+stringBuilder.toString();
            dynamicSchedulingList.get(0).setDay1(day);
        }
        else if (date2Parsing[0].equals(paymentDto.scheduleDate)){
            StringBuilder stringBuilder=new StringBuilder(date1Parsing[1]);
            stringBuilder.setCharAt(index, ch);
            String day=date1Parsing[0]+","+stringBuilder.toString();
            dynamicSchedulingList.get(0).setDay2(day);
        }
        else if (date3Parsing[0].equals(paymentDto.scheduleDate)){
            StringBuilder stringBuilder=new StringBuilder(date1Parsing[1]);
            stringBuilder.setCharAt(index, ch);
            String day=date1Parsing[0]+","+stringBuilder.toString();
            dynamicSchedulingList.get(0).setDay3(day);
        }
        dynamicSchedulingrepository.save(dynamicSchedulingList.get(0));
    }

    public List<TemporaryObjectHoldService>getDynamicScheduleTimeAndActiveStatus(String scheduleIdStr,String date){
        List<TemporaryObjectHoldService>scheduleTimeAndActiveStatus=new ArrayList<>();
        int scheduleId=Integer.parseInt(scheduleIdStr);
        List<Schedule>scheduleList=scheduleRepository.findByScheduleId(scheduleId);
        List<DynamicScheduling>dynamicSchedulingList=dynamicSchedulingrepository.findByScheduleId(scheduleId);
        String[]schedulesList=scheduleList.get(0).scheduleTimeStart.split(",");

        int day=LocalDate.parse(date).getDayOfWeek().getValue();
        System.out.println("abarrr subday paisi"+ day);
        System.out.println(day);
        String timestarts="";
        for (int i=0;i<schedulesList.length;i++){
            TemporaryObjectHoldService temporaryObjectHoldService=new TemporaryObjectHoldService();
            String [] spiltedScheduleList=schedulesList[i].split("~");
            if (day==Integer.parseInt(spiltedScheduleList[0])) {
                if (timestarts.equals("")){
                timestarts=spiltedScheduleList[1];
                }
                else {
                    timestarts=timestarts+","+spiltedScheduleList[1];
                }
            }
            }
        String[]schedules=timestarts.split(",");
        System.out.println(schedules.length);

        String [] date1Parsing=dynamicSchedulingList.get(0).day1.split(",");
        String [] date2Parsing=dynamicSchedulingList.get(0).day2.split(",");
        String [] date3Parsing=dynamicSchedulingList.get(0).day3.split(",");

        if (date1Parsing[0].equals(date)){
            for (int i=0;i<schedules.length;i++){
                TemporaryObjectHoldService temporaryObjectHoldService=new TemporaryObjectHoldService();
                    temporaryObjectHoldService.setScheduleTimeStr(schedulingService.AmPmFormetter(schedules[i]));
                if (date1Parsing[1].charAt(i)=='1'){
                    temporaryObjectHoldService.setActiveStatusBoolTime(true);
                }
                else {
                    temporaryObjectHoldService.setActiveStatusBoolTime(false);
                }
                scheduleTimeAndActiveStatus.add(temporaryObjectHoldService);
            }
        }
        else if (date2Parsing[0].equals(date)){
            for (int i=0;i<schedules.length;i++){
                TemporaryObjectHoldService temporaryObjectHoldService=new TemporaryObjectHoldService();

                    temporaryObjectHoldService.setScheduleTimeStr(schedulingService.AmPmFormetter(schedules[i]));
                if (date2Parsing[1].charAt(i)=='1'){
                    temporaryObjectHoldService.setActiveStatusBoolTime(true);
                }
                else {
                    temporaryObjectHoldService.setActiveStatusBoolTime(false);
                }
                scheduleTimeAndActiveStatus.add(temporaryObjectHoldService);
            }

        }
        else if (date3Parsing[0].equals(date)){
            for (int i=0;i<schedules.length;i++){
                TemporaryObjectHoldService temporaryObjectHoldService=new TemporaryObjectHoldService();
                    temporaryObjectHoldService.setScheduleTimeStr(schedulingService.AmPmFormetter(schedules[i]));
                if (date3Parsing[1].charAt(i)=='1'){
                    temporaryObjectHoldService.setActiveStatusBoolTime(true);
                }
                else {
                    temporaryObjectHoldService.setActiveStatusBoolTime(false);
                }
                scheduleTimeAndActiveStatus.add(temporaryObjectHoldService);
            }

        }
        return scheduleTimeAndActiveStatus;

    }
}
