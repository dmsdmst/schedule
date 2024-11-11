package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor

public class Schedule {

    private Long schedule_id ;
    private String password;
    private String name;
    private String title;
    private String contents;


    public Schedule(String password, String name, String title, String contents) {
        this.password = password;
        this.name = name;
        this.title = title;
        this.contents = contents;

    };

    public void updateName(String name) {
        this.name = name;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateContents(String contents) {
        this.contents = contents;
    }
}