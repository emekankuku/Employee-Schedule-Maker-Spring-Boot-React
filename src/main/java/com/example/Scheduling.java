package com.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.cglib.core.Local;

/**
 * Hello world!
 *
 */

public class Scheduling {
    public static void main(String[] args) throws ParseException {
        ZoneId z = ZoneId.of("US/Central");
        Year year = Year.now();
        LocalDate firstOfYear = year.atDay(1);
        ZonedDateTime start = firstOfYear.atStartOfDay(z);
        List<ZonedDateTime> zdts = new ArrayList<>();

        Duration duration = Duration.ofDays(1);
        ZonedDateTime zdt = start;
        // zdt = zdt.plus(duration);

        Calendar cal = Calendar.getInstance(Locale.US);
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String schedule = "02:00-22:00";
        Map<Integer, List<String>> calendar = new HashMap<>();

        while (zdt.getYear() == year.getValue()) {
            Date date = sdf.parse(zdt.getDayOfMonth() + "/" + zdt.getMonthValue() + "/" + year.getValue());
            cal.setTime(date);
            int week = cal.get(Calendar.WEEK_OF_YEAR);
            List<String> weeklySchedule = new ArrayList<>();
            while (cal.get(Calendar.WEEK_OF_YEAR) == week) {
                weeklySchedule
                        .add(zdt.getDayOfWeek() + ": " + schedule);
                zdt = zdt.plus(duration);
                date = sdf.parse(zdt.getDayOfMonth() + "/" + zdt.getMonthValue() + "/" + year.getValue());
                cal.setTime(date);
            }
            calendar.put(week, weeklySchedule);
        }

        // for(int i=1; i<=52; i++){
        //     System.out.println(calendar.get(i));
        // }
        // System.out.println(calendar.get(52));

        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        // TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        // String input = "09-26-2019";
        // LocalDate date = LocalDate.parse(input, dtf);
        // System.out.println(LocalDate.now().with(fieldUS, 7));
        // String input = "09-26-2019";
        // LocalDate date = LocalDate.parse(input, dtf);
        // cal.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        // System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        // System.out.println(Integer.toString(date.getYear()));
        // System.out.println(Integer.toString(date.getMonthValue()));
        // System.out.println(Integer.toString(date.getDayOfMonth()));

        System.out.println(Pattern.matches("([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]", "02:00-26:00"));

        

    }
}