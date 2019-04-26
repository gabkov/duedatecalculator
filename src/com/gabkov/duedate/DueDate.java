package com.gabkov.duedate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


public class DueDate {

    public LocalDateTime dueDateCalculator(LocalDateTime submitDate, int hours) throws NotWorkingHoursException, InvalidHoursException {
        if (submitDate.getHour() < 9 || (submitDate.getHour() >= 17))
            throw new NotWorkingHoursException("Work hours are between 9AM-5PM, please try again tomorrow.");
        if (hours < 1) throw new InvalidHoursException("Hours must be at least 1 or positive");

        LocalDateTime result = submitDate;
        int hourCount = 0;
        while(hourCount < hours){
            result = result.plusHours(1);
            if(!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                if(result.getHour() > 8 && result.getHour() < 17) {
                    hourCount++;
                }
            }else{
                result = result.plusDays(1);
            }
        }
        return result;
    }

    public static void main(String[] args) { }
}