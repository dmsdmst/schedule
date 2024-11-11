package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        //요청 받은 데이터로 객체 생성
        Schedule schedule = new Schedule(dto.getPassword(), dto.getName(), dto.getTitle(), dto.getContents());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleAll(String name, String creadteDate) {

        return scheduleRepository.findScheduleAll(name, creadteDate);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long scheduleId) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, String password, String name, String title, String contents) {

        if (title == null || name == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int updateSchedule = scheduleRepository.updateSchedule(scheduleId, password,name, title, contents);

        if (updateSchedule == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일정 " + scheduleId +"를 수정할 수 없습니다");
        }
//        else if (!Objects.equals(schedule.getPassword(), password)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다");
//        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);


        return new ScheduleResponseDto(schedule);

    }

    @Override
    public void deleteSchedule(Long scheduleId, String password) {

        int deleteSchedule = scheduleRepository.deleteSchedule(scheduleId, password);

        if (deleteSchedule ==  0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정 " + scheduleId +"를 삭제할 수 없습니다");
        }
//        else if (!Objects.equals(schedule.getPassword(), password)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다");
//        }


    }
}
