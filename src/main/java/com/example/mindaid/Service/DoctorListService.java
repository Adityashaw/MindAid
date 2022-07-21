package com.example.mindaid.Service;

import com.example.mindaid.Dto.ChooseDto;
import com.example.mindaid.Dto.ConcernDto;
import com.example.mindaid.Dto.DoctorsDto;
import com.example.mindaid.Model.Doctors;
import com.example.mindaid.Repository.DoctorConcernRepository;
import com.example.mindaid.Repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorListService {
    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    DoctorConcernRepository doctorConcernRepository;
    public List<DoctorsDto> getDoctorList(ConcernDto concernDto, ChooseDto chooseDto){
        List<DoctorsDto> finalDoctorList=new ArrayList<>();
        List<Object[]> chosenDoctors= doctorConcernRepository.findByConcernId(concernDto.concerns,chooseDto.contactMedia);
                for(Object[] doctors:chosenDoctors){
                    DoctorsDto doctor=new DoctorsDto();
                    doctor.setDocId((Integer) doctors[0]);
                    doctor.setName((String) doctors[1]);
                    doctor.setDescription((String) doctors[2]);
                    doctor.setSpeciality((String) doctors[3]);
                    doctor.setScheduleTime((String) doctors[4]);
                    doctor.setEducation((String) doctors[5]);
                    doctor.setScheduleDay((String) doctors[6]);
                    doctor.setScheduleTimeStart((String) doctors[7]);
                    doctor.setFee((Integer) doctors[8]);
                    doctor.setContactMedia((String) doctors[9]);
                    doctor.setScheduleday_parameter((String) doctors[10]);
                    doctor.setScheduleId((int)doctors[11]);
                    finalDoctorList.add(doctor);
                }
        return finalDoctorList;
    }
}
