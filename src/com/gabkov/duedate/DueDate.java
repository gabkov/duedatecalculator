package com.gabkov.duedate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * In my solution I use the work hours as a LinkedList. First I shift the values until the first element will be the
 * submit hour. Next I left shift as many hours are left-over (the not whole days).
 * Lastly I add the days to the submitted date and return the Due Date with the shifted hour.
 */

public class DueDate {

    public LocalDateTime dueDateCalculator(LocalDateTime submitDate, int hours) throws NotWorkingHoursException, InvalidHoursException {
        int submitHour = submitDate.getHour();

        if (submitHour < 9 || (submitHour >= 17))
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

    public static void main(String[] args) {
    }
}