package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

        // todo : 타임스탬프 구현하기 !!!

        // todo : 결과값에서 비밀번호 제외
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }


    // 전체 일정 조회

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findScheduleAll(
            @RequestBody ScheduleRequestDto dto
    ) {

        return new ResponseEntity<>(scheduleService.findScheduleAll(dto), HttpStatus.OK);
    }


    // 선택 일정 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId) {

        return new ResponseEntity<>(scheduleService.findScheduleById(scheduleId), HttpStatus.OK);
    }


    // 선택 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto dto
    ) {
        // todo : 오류 발생
        return new ResponseEntity<>(scheduleService.updateSchedule(scheduleId, dto.getPassword(), dto.getName(), dto.getTitle(), dto.getContents()), HttpStatus.OK);

    }



    // 선택 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto dto
    ) {
        scheduleService.deleteSchedule(scheduleId, dto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
