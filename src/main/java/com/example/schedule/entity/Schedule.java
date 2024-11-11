package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class Schedule {

    private Long schedule_id ;
    private String password;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


    public Schedule(String password, String name, String title, String contents) {
        this.password = password;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();

    };

    public Schedule(Long id, String name, String title, String contents, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.schedule_id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}