package com.example.mindaid.Controller.RestController;

import com.example.mindaid.Service.TemporaryConcernService;
import com.example.mindaid.Service.TemporaryObjectHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class PopulateDropdownController {
    @Autowired
    TemporaryObjectHoldService temporaryObjectHoldService;
    @Autowired
    TemporaryConcernService temporaryConcernService;

    @GetMapping("/testInt")
    public TemporaryConcernService getInteger(Model model){
        List<Integer>integerList=new ArrayList<>();
        Collections.addAll(integerList,1,2,2,3,4,5,6,7);
        return temporaryConcernService;
    }
    @RequestMapping(value = "/schedule-time/{date}/{docId}", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getAllSubcategories(@PathVariable("date") String date,@PathVariable("docId") String docId) {
        System.out.println("date:"+date);
        System.out.println("docId:"+docId);
        List<String>times=new ArrayList<>();
        Collections.addAll(times,"3 am","4 am");
        return times;
    }
}
