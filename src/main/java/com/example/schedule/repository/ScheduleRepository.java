package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findScheduleAll(String name, String createdDate);

    int deleteSchedule(Long scheduleId, String password);

    int  updateSchedule(Long scheduleId, String password, String name, String title, String contents);

    Schedule findScheduleByIdOrElseThrow(Long scheduleId);
}
