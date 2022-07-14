package com.example.mindaid.Service;

import com.example.mindaid.Dto.ConcernDto;
import com.example.mindaid.Model.Concern;
import com.example.mindaid.Repository.ConcernRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;


@Service
public class ConcernService {
    @Autowired
    ConcernRepository concernRepository;

    public void getAndSetConcernList(Model model){
        Concern concern=new Concern();
        ConcernDto concernDto=new ConcernDto();
        List<Concern> concernList=concernRepository.findAll();
        System.out.println(concernList.size());
        model.addAttribute(concernDto);
        model.addAttribute(concern);
        model.addAttribute("concernList",concernList);
    }
}
