package com.example.build.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
    List<String> emails = new ArrayList<>();
    List<Long> hours = new ArrayList<>();
    List<Double> accuracy = new ArrayList<>();
    Map<String, Long> checkinHours = new HashMap<>(); // Stores hours recorded with the checkin system
    Map<String, Double> actualHours = new HashMap<>(); // Stores expected hours during a period according to user
                                                       // schedules
    Map<String, Double> accuracyMap = new HashMap<>();
    Map<String, List<DaysOff>> daysOffMap = new HashMap<>();
    List<CheckIn> checkIns;
    List<Schedule> schedules;
    List<DaysOff> daysOff;
    LocalDate twoWeeksAgo = LocalDate.now().minusDays(14);
    long pastDays; // Indicates number of past days to be analyzed

    public Report() {

    }

    public Report(List<CheckIn> checkIns, List<String> emails, List<Schedule> schedules, List<DaysOff> daysOff, long pastDays) {
        this.checkIns = checkIns;
        this.emails = emails;
        this.schedules = schedules;
        this.daysOff = daysOff;
        this.pastDays = pastDays;
        for (DaysOff dayOff : daysOff) {
            String email = dayOff.getEmail();
            if (daysOffMap.containsKey(email)) {
                List<DaysOff> temp = daysOffMap.get(email);
                temp.add(dayOff);
                daysOffMap.put(email, temp);
            } else
                daysOffMap.put(email, new ArrayList<>(Arrays.asList(dayOff)));

        }

        Collections.sort(this.emails); // Sort emails in alphabetical order

        declareCheckinHours(checkIns, emails);
        declareActualHours(schedules);

    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<Long> getHours() {
        return hours;
    }

    public void setHours(List<Long> hours) {
        this.hours = hours;
    }

    public List<Double> getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(List<Double> accuracy) {
        this.accuracy = accuracy;
    }

    public Map<String, Long> getCheckinHours() {
        return checkinHours;
    }

    public void setCheckinHours(Map<String, Long> checkinHours) {
        this.checkinHours = checkinHours;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Map<String, Double> getActualHours() {
        return actualHours;
    }

    public void setActualHours(Map<String, Double> actualHours) {
        this.actualHours = actualHours;
    }

    public List<DaysOff> getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(List<DaysOff> daysOff) {
        this.daysOff = daysOff;
    }

    public void declareCheckinHours(List<CheckIn> checkIns, List<String> emails) {
        Map<String, LocalDateTime> userTimes = new HashMap<>(); // Temporary map
        for (String email : emails) {
            userTimes.put(email, null);
            checkinHours.put(email, 0L);
        }
        for (CheckIn checkIn : checkIns) {
            String email = checkIn.getEmail();
            if (checkIn.getStatus() == true) {
                userTimes.put(email, checkIn.getLt());
            } else if (userTimes.get(email) != null) {
                long len = userTimes.get(email).until(checkIn.getLt(), ChronoUnit.HOURS);
                if (checkinHours.containsKey(email))
                    checkinHours.put(email, checkinHours.get(email) + len);
                else
                    checkinHours.put(email, len);
                userTimes.put(email, null);
            }
        }
    }

    public void declareActualHours(List<Schedule> schedules) {
        for (Schedule schedule : schedules) {
            String email = schedule.getEmail();
            Map<String, String> timeRanges = schedule.scheduleMap();
            List<DaysOff> daysOff = daysOffMap.get(email);
            long pastDays = this.pastDays;
            while (pastDays > 0) {
                LocalDate date = LocalDate.now().minusDays(pastDays--);
                String day = date.getDayOfWeek().toString().toLowerCase();
                boolean notWorked = false;
                if (daysOff != null) {
                    for (DaysOff dayOff : daysOff) {
                        LocalDate start = dayOff.getStart();
                        LocalDate end = dayOff.getEnd();
                        if ((date.isAfter(start) || date.equals(start)) && (date.isBefore(end) || date.equals(end))) {
                            notWorked = true;
                            break;
                        }
                    }
                }
                if (timeRanges.get(day).length() == 0 || notWorked)
                    continue;
                String[] range = timeRanges.get(day).split("-");
                Double[] totalMinutes = new Double[range.length];
                for (int i = 0; i < range.length; i++) {
                    double total = 0;
                    String[] hourToMinute = range[i].split(":");
                    total += 60 * Double.parseDouble(hourToMinute[0]) + Double.parseDouble(hourToMinute[1]);
                    totalMinutes[i] = total / 60;
                }
                double totalHours = totalMinutes[1] - totalMinutes[0];
                if (actualHours.containsKey(email))
                    actualHours.put(email, actualHours.get(email) + totalHours);
                else
                    actualHours.put(email, totalHours);
            }
        }
    }

}
