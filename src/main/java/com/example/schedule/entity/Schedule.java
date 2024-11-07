package com.example.schedule.entity;

import com.example.schedule.dto.scheduleRequestDto;
import com.example.schedule.dto.scheduleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class Schedule {
    private Long schedule_id ;
    private String title;
    private String contents;
    private String password;
    private String name;
    private String createdDate;
    private String updatedDate;

    public void update(scheduleRequestDto dto) {
        this.title = dto.getTitle();
        this.name = dto.getName();
    }
}