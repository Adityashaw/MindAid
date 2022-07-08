package com.example.mindaid.Service;

import com.example.mindaid.Dto.ConcernDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TemporaryConcernService {
    public List<ConcernDto> chooseList= new ArrayList<>();

    public List<ConcernDto> getChooseList() {
        return chooseList;
    }

    public void setChooseList(List<ConcernDto> chooseList) {
        this.chooseList = chooseList;
    }
}
