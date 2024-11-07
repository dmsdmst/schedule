package com.example.schedule.controller;

import com.example.schedule.dto.scheduleRequestDto;
import com.example.schedule.dto.scheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class scheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();


    // 일정 생성
    @PostMapping("/schedule")
    public scheduleResponseDto createSchedul(@RequestBody scheduleResponseDto dto) {

        // 식별자 1부터 시작
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

        // todo : 타임스탬프 구현하기 !!!

        Schedule schedule = new Schedule(scheduleId, dto.getPassword(), dto.getName(), dto.getTitle(), dto.getContents(), dto.getCreatedDate(), dto.getUpdatedDate());
        scheduleList.put(scheduleId,schedule);

        // todo : 결과값에서 비밀번호 제외
        return new scheduleResponseDto(schedule);
    }


    // todo : 전체 일정 조회

    // 선택 일정 조회
    @GetMapping("/schedule/{schedulId}")
    public scheduleResponseDto findschedulebyId(@PathVariable Long schedulId) {

        Schedule schedule = scheduleList.get(schedulId);

        return new scheduleResponseDto(schedule);
    }


    // 선택 일정 수정
    @PutMapping("/schedule/{scheduleId}")
    public scheduleResponseDto updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody scheduleRequestDto dto
    ) {
        Schedule schedule = scheduleList.get(scheduleId);

        schedule.update(dto);

        return new scheduleResponseDto(schedule);
    }



    // 선택 일정 삭제
    @DeleteMapping("/schedule/{scheduleId}")
    public String deleteSchedul(@PathVariable Long scheduleId) {

        scheduleList.remove(scheduleId);
        return scheduleId + "번 일정이 삭제되었습니다";
    }
}
