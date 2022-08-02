package com.example.mindaid.Dto;

import java.util.ArrayList;
import java.util.List;

public class DoctorsScheduleDto {
    public int docId;
    public String day;
    public String dayParameter;
    public String slot1;
    public String slot2;
    public String slot3;
    public List<String>chosenConcernList=new ArrayList<>();
    public String [] concerns=new String[20];
    public List<String> selectedSlotMessage=new ArrayList<>();
    public List<String> selectedSlotLive=new ArrayList<>();

    public List<String> getSelectedSlotMessage() {
        return selectedSlotMessage;
    }

    public void setSelectedSlotMessage(List<String> selectedSlotMessage) {
        this.selectedSlotMessage = selectedSlotMessage;
    }

    public List<String> getSelectedSlotLive() {
        return selectedSlotLive;
    }

    public void setSelectedSlotLive(List<String> selectedSlotLive) {
        this.selectedSlotLive = selectedSlotLive;
    }

    public String[] getConcerns() {
        return concerns;
    }

    public void setConcerns(String[] concerns) {
        this.concerns = concerns;
    }

    public List<String> getChosenConcernList() {
        return chosenConcernList;
    }

    public void setChosenConcernList(List<String> chosenConcernList) {
        this.chosenConcernList = chosenConcernList;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDayParameter() {
        return dayParameter;
    }

    public void setDayParameter(String dayParameter) {
        this.dayParameter = dayParameter;
    }

    public String getSlot1() {
        return slot1;
    }

    public void setSlot1(String slot1) {
        this.slot1 = slot1;
    }

    public String getSlot2() {
        return slot2;
    }

    public void setSlot2(String slot2) {
        this.slot2 = slot2;
    }

    public String getSlot3() {
        return slot3;
    }

    public void setSlot3(String slot3) {
        this.slot3 = slot3;
    }
}
