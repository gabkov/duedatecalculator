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
    private LocalDateTime submitDate;

    @BeforeEach
    public void init() {
        dueDate = new DueDate();
        submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 14, 14);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResult8HoursTest() throws NotWorkingHoursException, InvalidHoursException {
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 8);

        String expectedDateString = "2019-04-26 14:14";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResult32HoursTest() throws NotWorkingHoursException, InvalidHoursException {
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 32);

        String expectedDateString = "2019-05-01 14:14";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResult80HoursTest() throws NotWorkingHoursException, InvalidHoursException {
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 80);

        String expectedDateString = "2019-05-09 14:14";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResult1WeekTest() throws NotWorkingHoursException, InvalidHoursException {
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 168);

        String expectedDateString = "2019-05-24 14:14";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResultSubmitHourStartsFrom9Add9Test() throws NotWorkingHoursException, InvalidHoursException {
        submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 9, 14);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 9);

        String expectedDateString = "2019-04-26 10:14";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResultSubmitHourStartsFrom9Add5Test() throws NotWorkingHoursException, InvalidHoursException {
        submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 9, 14);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 5);

        String expectedDateString = "2019-04-25 14:14";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResultSubmitHourStartsFrom10Add5Test() throws NotWorkingHoursException, InvalidHoursException {
        submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 10, 14);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 5);

        String expectedDateString = "2019-04-25 15:14";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResultSubmitHourStartsFrom17Test() throws NotWorkingHoursException, InvalidHoursException {
        submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 17, 14);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 5);

        String expectedDateString = "2019-04-26 14:14";
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
        LinkedList<Integer> expected = new LinkedList<Integer>(Arrays.asList(13, 14, 15, 16, 17, 9, 10, 11, 12));

        assertEquals(expected, resultList);
    }
}