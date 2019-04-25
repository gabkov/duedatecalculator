package com.gabkov.duedate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;

public class DueDate {

    public LocalDateTime dueDateCalculator(LocalDateTime submitDate, int hours){
        if (hours < 1) {
            return submitDate;
        }
        int submitHour = submitDate.getHour();

        LinkedList<Integer> hoursArray = getHoursArrayStartsWithSubmitHour(submitHour);
        int passedDays = 0;

        for(int i = 0; i < hours; i++){
            Integer firstHourValue = hoursArray.removeFirst();
            if(firstHourValue == 9){
                passedDays++;
                hoursArray.addLast(firstHourValue);
                firstHourValue = hoursArray.removeFirst();
            }
            hoursArray.addLast(firstHourValue);
        }

        LocalDateTime result = submitDate;
        int addedDays = 0;
        while (addedDays < passedDays) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        result = result.with(LocalTime.of(hoursArray.getFirst(), 0));

        return result;
    }

    private LinkedList<Integer> getHoursArrayStartsWithSubmitHour(int submitHour){
        Integer[] hours = {9, 10, 11, 12, 13, 14, 15, 16, 17};
        LinkedList<Integer> hoursLinked = new LinkedList<>(Arrays.asList(hours));

        for(int i = 0; i < hours.length; i++){
            Integer first = hoursLinked.getFirst();
            if(first == submitHour){
                break;
            }
            Integer firstHour = hoursLinked.removeFirst();
            hoursLinked.addLast(firstHour);
        }
        return hoursLinked;
    }

    public static void main(String[] args) {
        // write your code here
    }
}
