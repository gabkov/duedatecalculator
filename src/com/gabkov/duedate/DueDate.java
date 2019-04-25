package com.gabkov.duedate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;

public class DueDate {

    public LocalDateTime dueDateCalculator(LocalDateTime submitDate, int hours) throws NotWorkingHoursException {
        int submitHour = submitDate.getHour();

        if(submitHour < 9 || submitHour > 17) throw new NotWorkingHoursException("Work hours are between 9AM-5PM, please try again tomorrow.");
        if (hours < 1) return submitDate;

        LinkedList<Integer> hoursList = getHoursListStartsWithSubmitHour(submitHour);

        int passedDays = 0;
        for(int i = 0; i < hours; i++){
            Integer firstHourValue = hoursList.removeFirst();
            if(firstHourValue == 9){
                passedDays++;
                // an extra shift when a day passes 9 --> 10
                hoursList.addLast(firstHourValue);
                firstHourValue = hoursList.removeFirst();
            }
            hoursList.addLast(firstHourValue);
        }

        LocalDateTime result = submitDate;
        int addedDays = 0;
        while (addedDays < passedDays) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                addedDays++;
            }
        }
        result = result.with(LocalTime.of(hoursList.getFirst(), 0));
        return result;
    }

    private LinkedList<Integer> getHoursListStartsWithSubmitHour(int submitHour){
        Integer[] hours = {9, 10, 11, 12, 13, 14, 15, 16, 17};
        LinkedList<Integer> hoursLinked = new LinkedList<>(Arrays.asList(hours));

        while(hoursLinked.getFirst() != submitHour){
            Integer first = hoursLinked.removeFirst();
            hoursLinked.addLast(first);
        }
        return hoursLinked;
    }

    public static void main(String[] args) {}
}
