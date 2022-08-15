package com.example.mindaid.Service;

import com.example.mindaid.Dto.Admin.AppointmentDto;
import com.example.mindaid.Model.Doctors;
import com.example.mindaid.Model.Payment;
import com.example.mindaid.Model.Schedule;
import com.example.mindaid.Model.User;
import com.example.mindaid.Repository.DoctorsRepository;
import com.example.mindaid.Repository.PaymentRepository;
import com.example.mindaid.Repository.ScheduleRepository;
import com.example.mindaid.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    UserRepository userRepository;


    public List<AppointmentDto> getAppointmentListAdmin(String approval){
        List<Payment> paymentList= paymentRepository.findAll();
        Collections.reverse(paymentList);
        List<AppointmentDto> appointmentDtoList=new ArrayList<>();
        for(Payment payment: paymentList){
            AppointmentDto appointmentDto=new AppointmentDto();
            appointmentDto.setAppointmentDate(payment.getScheduleDate());
            appointmentDto.setAppointmentTime(payment.getScheduleTime());
            appointmentDto.setContactMedia(payment.getContactMedia());
            appointmentDto.setUserCard(payment.getCardNumber());
            List<Schedule> scheduleList=scheduleRepository.findByScheduleId(payment.getScheduleId());
            appointmentDto.setFee(scheduleList.get(0).getFee());
            List<Doctors> getDoctors=doctorsRepository.findByDocId(payment.getDocId());
            appointmentDto.setDocName(getDoctors.get(0).getName());
            User getUser=userRepository.findByUserId(payment.getUserId());
            appointmentDto.setUserName(getUser.getName());
            appointmentDto.setPaymentId(payment.getPaymentId());
            appointmentDto.setPhotos(getDoctors.get(0).getPhotos());
            appointmentDtoList.add(appointmentDto);

        }
        return appointmentDtoList;
    }

    public String getAllDoctors(Model model){
        List<Doctors> pendingDoctorList= doctorsRepository.findByApproval("contacted");
        List<Integer> ButtonFlagNewTherapist=new ArrayList<>();
        List<Integer> ButtonFlagAllDoctors=new ArrayList<>();
        ButtonFlagAllDoctors.add(1);
//        for (Doctors doctors:pendingDoctorList){
//            doctors.setPhotos("\\assets\\user-photos\\"+doctors.getDocId()+ "\\"+doctors.getPhotos());
//        }
        List<AppointmentDto> appointmentListAdmin=new ArrayList<>();
        String status="All Therapists";
        Doctors doctors=new Doctors();
        List<Doctors> pendingDoctorList2= doctorsRepository.findByApproval("pending");
        int notification= pendingDoctorList2.size();
        pendingDoctorList2.clear();
        model.addAttribute("notification",notification);
        model.addAttribute("pendingDoctorList", pendingDoctorList);
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("status", status);
        model.addAttribute("flag1",ButtonFlagNewTherapist);
        model.addAttribute("flag2",ButtonFlagAllDoctors);
        model.addAttribute(doctors);
        return "adminProfile";

    }

}
