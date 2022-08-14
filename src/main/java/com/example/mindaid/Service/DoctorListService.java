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
    public List<DoctorsDto> getDoctorList(List<Integer> concerns, ChooseDto chooseDto){
        List<DoctorsDto> finalDoctorList=new ArrayList<>();
        List<Object[]> chosenDoctors= doctorConcernRepository.findByConcernId(concerns,chooseDto.contactMedia);
                for(Object[] doctors:chosenDoctors){
                    DoctorsDto doctor=new DoctorsDto();
                    doctor.setDocId((Integer) doctors[0]);
                    doctor.setName((String) doctors[1]);
                    doctor.setDescription((String) doctors[2]);
                    doctor.setSpeciality((String) doctors[3]);
                    doctor.setEducation((String) doctors[4]);
                    doctor.setExperience((String) doctors[5]);
                    doctor.setEmail((String) doctors[6]);
                    doctor.setMobile((String) doctors[7]);
                    doctor.setAge((String) doctors[8]);
                    doctor.setGender((String) doctors[9]);
                    doctor.setAppliedDate((String) doctors[13]);
                    doctor.setPhotos("\\assets\\user-photos\\"+doctor.getDocId()+ "\\"+(String)doctors[14]);
                    doctor.setPatientCount((int)doctors[15]);
                    doctor.setRatings((String) doctors[16]);
                    doctor.setScheduleId((int)doctors[17]);
                    doctor.setScheduleDay((String) doctors[18]);
                    doctor.setScheduleTimeStart((String) doctors[19]);
                    doctor.setFee((int) doctors[20]);
                    doctor.setContactMedia((String) doctors[21]);
                    doctor.setScheduleday_parameter((String) doctors[22]);
                    finalDoctorList.add(doctor);
                }
        return finalDoctorList;
    }
}
