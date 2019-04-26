package com.gabkov.duedate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * In my solution I use the work hours as a LinkedList. First I shift the values until the first element will be the
 * submit hour. Next I left shift as many hours are passed to the dueDateCalculator and every new day (firstElement == 9) is incrementing
 * the passedDays variable. Lastly I add the days to the submitted date and return the Due Date with the shifted hour.
 */

public class DueDate {

    public LocalDateTime dueDateCalculator(LocalDateTime submitDate, int hours) throws NotWorkingHoursException, InvalidHoursException {
        int submitHour = submitDate.getHour();

        if (submitHour < 9 || submitHour > 17)
            throw new NotWorkingHoursException("Work hours are between 9AM-5PM, please try again tomorrow.");
        if (hours < 1) throw new InvalidHoursException("Hours must be at least 1 or positive");

        LinkedList<Integer> hoursList = getHoursListStartsWithSubmitHour(submitHour);

        int passedDays = getPassedDaysAndShiftHoursList(hoursList, hours);
        // the hoursList first element is the needed hour value after the required shifts
        int shiftedHour = hoursList.getFirst();

        LocalDateTime result = submitDate;
        int addedDays = 0;
        while (addedDays < passedDays) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                addedDays++;
            }
        }
        result = result.with(LocalTime.of(shiftedHour, submitDate.getMinute()));
        return result;
    }

    private int getPassedDaysAndShiftHoursList(LinkedList<Integer> hoursList, int hours) {
        int passedDays = 0;
        // left shift as many hours passed
        for (int i = 0; i < hours; i++) {
            Integer firstElement = hoursList.removeFirst();
            if (firstElement == 9 && i > 0) {
                passedDays++;
                // i-- for an extra shift when a day passed 9 --> 10
                i--;
            }
            hoursList.addLast(firstElement);
        }
        return passedDays;
    }

    public LinkedList<Integer> getHoursListStartsWithSubmitHour(int submitHour) {
        Integer[] hours = {9, 10, 11, 12, 13, 14, 15, 16, 17};
        LinkedList<Integer> hoursLinked = new LinkedList<>(Arrays.asList(hours));
        // shift the values until the submitHour is the first element
        while (hoursLinked.getFirst() != submitHour) {
            Integer first = hoursLinked.removeFirst();
            hoursLinked.addLast(first);
        }
        return hoursLinked;
    }

    public static void main(String[] args) {
    }
}