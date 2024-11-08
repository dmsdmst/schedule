package com.example.schedule.controller;

import com.example.schedule.dto.scheduleRequestDto;
import com.example.schedule.dto.scheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/schedule")
public class scheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();


    // 일정 생성
    @PostMapping
    public ResponseEntity<scheduleResponseDto> createSchedul(@RequestBody scheduleResponseDto dto) {

        // 식별자 1부터 시작
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

        // todo : 타임스탬프 구현하기 !!!

        Schedule schedule = new Schedule(scheduleId, dto.getPassword(), dto.getName(), dto.getTitle(), dto.getContents(), dto.getCreatedDate(), dto.getUpdatedDate());
        scheduleList.put(scheduleId,schedule);

        // todo : 결과값에서 비밀번호 제외
        return new ResponseEntity<>(new scheduleResponseDto(schedule), HttpStatus.CREATED);
    }


    // 전체 일정 조회

    @GetMapping
    public ResponseEntity<List<scheduleResponseDto>> findscheduleAll(
            @RequestBody scheduleRequestDto dto
    ) {

        List<scheduleResponseDto> responseList = new ArrayList<>();

        responseList = scheduleList.values().stream().map(scheduleResponseDto::new).toList();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }


    // 선택 일정 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<scheduleResponseDto> findscheduleById(@PathVariable Long scheduleId) {

        Schedule schedule = scheduleList.get(scheduleId);

        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new scheduleResponseDto(schedule), HttpStatus.OK);
    }


    // 선택 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<scheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody scheduleRequestDto dto
    ) {
        Schedule schedule = scheduleList.get(scheduleId);


        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!Objects.equals(schedule.getPassword(), dto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (dto.getName() != null) {
           schedule.updateName(dto);
        }

        if (dto.getTitle() != null) {
            schedule.updateTitle(dto);
        }

        if (dto.getContents() != null) {
            schedule.updateContents(dto);
        }

        return new ResponseEntity<>(new scheduleResponseDto(schedule), HttpStatus.OK);

    }



    // 선택 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedul(
            @PathVariable Long scheduleId,
            @RequestBody scheduleRequestDto dto
    ) {
        if (scheduleList.containsKey(scheduleId) && Objects.equals(scheduleList.get(scheduleId).getPassword(), dto.getPassword())) {

            scheduleList.remove(scheduleId);

            return new ResponseEntity<>(HttpStatus.OK);
        } else if(!scheduleList.containsKey(scheduleId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
