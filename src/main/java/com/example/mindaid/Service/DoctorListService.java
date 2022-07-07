package com.example.mindaid.Service;

import com.example.mindaid.Dto.ConcernDto;
import com.example.mindaid.Dto.DoctorsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorListService {
    public List<DoctorsDto> getDoctorList(ConcernDto concernDto){
        for(int id: concernDto.concerns){
            if (id>0){
                

            }
        }

    }
}
