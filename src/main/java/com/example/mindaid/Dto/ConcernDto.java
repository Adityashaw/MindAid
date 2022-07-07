package com.example.mindaid.Dto;

import java.util.ArrayList;
import java.util.List;

public class ConcernDto {
    public int[]concerns=new int[18];
    public List<String>concernL=new ArrayList<>();

    public int[] getConcerns() {
        return concerns;
    }

    public void setConcerns(int[] concerns) {
        this.concerns = concerns;
    }
}
