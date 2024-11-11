package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class ScheduleResponseDto {

    private Long schedule_id ;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


    public ScheduleResponseDto(Long id, String name, String title, String contents, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.schedule_id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.schedule_id = schedule.getSchedule_id();
        this.name = schedule.getName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdDate = schedule.getCreatedDate();
        this.updatedDate = schedule.getUpdatedDate();
    }
}
