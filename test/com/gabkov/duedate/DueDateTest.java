package com.gabkov.duedate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DueDateTest {

    private DueDate dueDate;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @BeforeEach
    public void init(){
        dueDate = new DueDate();
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResult8HoursTest() {
        LocalDateTime submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 14, 0);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 8);

        String expectedDateString = "2019-04-26 14:00";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }


    @Test
    public void dueDateCalculatorReturnsExpectedResult32HoursTest() {
        LocalDateTime submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 14, 0);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 32);

        String expectedDateString = "2019-05-01 14:00";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResult80HoursTest() {
        LocalDateTime submitDate = LocalDateTime.of(2019, Month.APRIL, 25, 14, 0);
        LocalDateTime resultDate = dueDate.dueDateCalculator(submitDate, 80);

        String expectedDateString = "2019-05-09 14:00";
        String resultDateString = resultDate.format(formatter);

        assertEquals(expectedDateString, resultDateString);
    }
}