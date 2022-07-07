package com.example.mindaid.Service;

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
    public List<Doctors> getDoctorList(ConcernDto concernDto){
        List<Doctors> finalDoctorList=new ArrayList<>();
        for(int id: concernDto.concerns){
            if (id>0){
                List<Doctors> chosenDoctors= doctorConcernRepository.findByConcernId(id);
                for(Doctors doctors:chosenDoctors){
                    finalDoctorList.add(doctors);
                }

            }
        }
        return finalDoctorList;

    }
}
