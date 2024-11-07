package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class scheduleResponseDto {

    private Long schedule_id ;
    private String password;
    private String name;
    private String title;
    private String contents;
    private String createdDate;
    private String updatedDate;


    public scheduleResponseDto(Schedule schedule) {
        this.schedule_id = schedule.getSchedule_id();
        this.password = schedule.getPassword();
        this.name = schedule.getName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdDate = schedule.getCreatedDate();
        this.updatedDate = schedule.getUpdatedDate();
    }
}
