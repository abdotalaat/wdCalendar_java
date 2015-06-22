/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abdobean.wdcalendar.controller;

import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author abdo.talaat
 */
public class utilities {

    public static String[] getWeekRange(String date) {

        String[] dateTimes = new String[2];
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
        DateTime dt = formatter.parseDateTime(date);
        DateTime weekStart = dt.withDayOfWeek(DateTimeConstants.MONDAY).withTimeAtStartOfDay();
        DateTime weekEnd = dt.withDayOfWeek(DateTimeConstants.SUNDAY).plusDays(1).withTimeAtStartOfDay();

        dateTimes[0] = weekStart.toString(formatter);
        dateTimes[1] = weekEnd.toString(formatter);
        System.out.println("now: " + dt);
        System.out.println("weekStart: " + weekStart);
        System.out.println("weekEnd: " + weekEnd);
        return dateTimes;
    }
}
