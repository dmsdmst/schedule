package com.example.schedule.entity;

import com.example.schedule.dto.scheduleRequestDto;
import com.example.schedule.dto.scheduleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class Schedule {
    private Long schedule_id ;
    private String password;
    private String name;
    private String title;
    private String contents;
    private String createdDate;
    private String updatedDate;

    public void updateName(scheduleRequestDto dto) {
        this.contents = dto.getContents();
    }

    public void updateTitle(scheduleRequestDto dto) {
        this.title = dto.getTitle();
    }
    public void updateContents(scheduleRequestDto dto) {
        this.contents = dto.getContents();
    }
}