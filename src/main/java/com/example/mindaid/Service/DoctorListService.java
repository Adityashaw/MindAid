package com.example.mindaid.Service;

import com.example.mindaid.Dto.ChooseDto;
import com.example.mindaid.Dto.ConcernDto;
import com.example.mindaid.Dto.DoctorsDto;
import com.example.mindaid.Model.Doctors;
import com.example.mindaid.Repository.DoctorConcernRepository;
import com.example.mindaid.Repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorListService {
    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    DoctorConcernRepository doctorConcernRepository;
    public List<Doctors> getDoctorList(ConcernDto concernDto, ChooseDto chooseDto){
        List<Doctors> finalDoctorList=new ArrayList<>();
        List<Object[]> chosenDoctors= doctorConcernRepository.findByConcernId(concernDto.concerns,chooseDto.contactMedia);
                for(Object[] doctors:chosenDoctors){
                    Doctors doctor=new Doctors();
                    doctor.setDoc_id((Integer) doctors[0]);
                    doctor.setName((String) doctors[1]);
                    doctor.setDescription((String) doctors[2]);
                    doctor.setSpeciality((String) doctors[3]);
                    doctor.setSchedule((String) doctors[4]);
                    finalDoctorList.add(doctor);
                }
        return finalDoctorList;
    }
}
