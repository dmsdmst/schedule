package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findScheduleAll(ScheduleRequestDto dto);

    ScheduleResponseDto findScheduleById(Long scheduleId);

    ScheduleResponseDto updateSchedule(Long scheduleId, String password, String name, String title, String contents);

    void deleteSchedule(Long scheduleId, String password);
}
