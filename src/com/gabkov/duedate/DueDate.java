package com.gabkov.duedate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class DueDate {


    public LocalDateTime dueDateCalculator(LocalDateTime submitDate, int hours){
        if (hours < 1) {
            return submitDate;
        }
        int submitHour = submitDate.getHour();

        int[] hoursArray = getHoursArrayStartsWithSubmitHour(submitHour);
        int passedDays = 0;

        for(int i = 0; i < hours; i++){
            int firstHourValue = hoursArray[0];
            if(firstHourValue == 9){
                passedDays++;
                System.arraycopy(hoursArray, 1, hoursArray , 0, hoursArray.length-1);
                hoursArray[hoursArray.length-1] = firstHourValue;
                firstHourValue = hoursArray[0];
            }
            System.arraycopy(hoursArray, 1, hoursArray , 0, hoursArray.length-1);
            hoursArray[hoursArray.length-1] = firstHourValue;

        }

        LocalDateTime result = submitDate;
        int addedDays = 0;
        while (addedDays < passedDays) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }

        result = result.with(LocalTime.of(hoursArray[0], 0));

        System.out.println(result);

        return result;
    }

    private int[] getHoursArrayStartsWithSubmitHour(int submitHour){
        int[] hours = {9, 10, 11, 12, 13, 14, 15, 16, 17};

        for(int i = 0; i < hours.length; i++){
            int first = hours[0];
            if(first == submitHour){
                System.out.println(Arrays.toString(hours));
                return hours;
            }
            System.arraycopy(hours, 1, hours , 0, hours.length-1);
            hours[hours.length-1] = first;
        }
        return null;
    }

    public static void main(String[] args) {
        // write your code here
    }
}
