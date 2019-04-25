package com.gabkov.duedate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DueDateTest {

    private DueDate dueDate;

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd HH:mm");

    @BeforeEach
    public void init(){
        dueDate = new DueDate();
    }

    @Test
    public void dueDateCalculatorReturnsExpectedResultTest() {
        Date submitDate = new Calendar.Builder()
                .setDate(2019, 4, 25)
                .setTimeOfDay(14, 0, 0)
                .build().getTime();

        Date resultDate = dueDate.dueDateCalculator(submitDate, 8);

        Date expectedDate = new Calendar.Builder()
                .setDate(2019, 4, 26)
                .setTimeOfDay(14, 0, 0)
                .build().getTime();

        String resultDateString = formatter.format(resultDate);

        String expectedDateString = formatter.format(expectedDate);

        assertEquals(resultDateString, expectedDateString);
    }
}