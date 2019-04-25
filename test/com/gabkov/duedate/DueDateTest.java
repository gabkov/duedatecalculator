package com.gabkov.duedate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DueDateTest {

    private DueDate dueDate;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @BeforeEach
    public void init() {
        dueDate = new DueDate();
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResult8HoursTest() throws NotWorkingHoursException, InvalidHoursException {
        LocalDateTime submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 14, 14);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 8);

        String expectedDateString = "2019-04-26 14:14";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResult32HoursTest() throws NotWorkingHoursException, InvalidHoursException {
        LocalDateTime submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 14, 58);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 32);

        String expectedDateString = "2019-05-01 14:58";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResult80HoursTest() throws NotWorkingHoursException, InvalidHoursException {
        LocalDateTime submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 14, 0);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 80);

        String expectedDateString = "2019-05-09 14:00";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorThrowsExceptionForInvalidSubmitTimeTest() {
        LocalDateTime submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 18, 0);

        assertThrows(NotWorkingHoursException.class, () -> dueDate.dueDateCalculator(submitDate, 80));
    }

    @Test
    public void dueDateCalculatorThrowsExceptionForInvalidHourInput() {
        LocalDateTime submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 15, 0);

        assertThrows(InvalidHoursException.class, () -> dueDate.dueDateCalculator(submitDate, -2));
    }

    @Test
    public void getHoursListStartsWithSubmitHourWorksProperlyTest() {
        LinkedList<Integer> resultList = dueDate.getHoursListStartsWithSubmitHour(13);
        LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(13, 14, 15, 16, 17, 9, 10, 11, 12));

        assertEquals(expected, resultList);
    }
}