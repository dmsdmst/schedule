package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ScheduleResponseDto {

    private Long schedule_id ;
    private String password;
    private String name;
    private String title;
    private String contents;


    public ScheduleResponseDto(Schedule schedule) {
        this.schedule_id = schedule.getSchedule_id();
        this.password = schedule.getPassword();
        this.name = schedule.getName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }
}
