package com.example.build.sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.build.dto.UserDto;
import com.example.build.dto.DaysOffDtos.DaysOffOutputDto;
import com.example.build.dto.GroupDtos.GroupDto;
import com.example.build.dto.ScheduleDtos.SchedOutput;

public class Sorting {

    public List<GroupDto> sortGroups(List<GroupDto> list) {
        Collections.sort(list, new Comparator<GroupDto>() {
            public int compare(GroupDto g1, GroupDto g2) {
                return g1.getName().compareTo(g2.getName());
            }
        });

        return list;
    }

    public List<UserDto> sortUsers(List<UserDto> list) {
        Collections.sort(list, new Comparator<UserDto>() {
            public int compare(UserDto u1, UserDto u2) {
                return u1.getFirstName().compareTo(u2.getFirstName());
            }
        });

        return list;
    }

    public List<SchedOutput> sortSchedules(List<SchedOutput> list) {
        Collections.sort(list, new Comparator<SchedOutput>() {
            public int compare(SchedOutput s1, SchedOutput s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });

        return list;
    }

    public List<DaysOffOutputDto> sortDaysOff(List<DaysOffOutputDto> list) {
        Collections.sort(list, new Comparator<DaysOffOutputDto>() {
            public int compare(DaysOffOutputDto d1, DaysOffOutputDto d2) {
                return d1.getEnd().compareTo(d2.getName());
            }
        });

        return list;
    }
}
