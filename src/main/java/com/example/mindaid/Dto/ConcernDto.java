package com.example.mindaid.Dto;

import java.util.ArrayList;
import java.util.List;

public class ConcernDto {
    public String[]concerns=new String[100];
    public List<String>concernL=new ArrayList<>();

    public String[] getConcerns() {
        return concerns;
    }

    public void setConcerns(String[] concerns) {
        this.concerns = concerns;
    }
}
