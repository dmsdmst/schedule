package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository

public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("scheduleId");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("password", schedule.getPassword());
        parameters.put("name", schedule.getName());
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto
                (key.longValue(), schedule.getPassword(), schedule.getName(), schedule.getTitle(), schedule.getContents());
    }

    @Override
    public List<ScheduleResponseDto> findScheduleAll() {


        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    @Override
    public Optional<Schedule> findScheduleById(Long scheduleId) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where scheduleId = ?", scheduleRowMapperV2(), scheduleId);
        return result.stream().findAny();
    }

    @Override
    public int deleteSchedule(Long scheduleId) {
        return jdbcTemplate.update("delete from schedule where scheduleId = ?", scheduleId);

    }

    @Override
    public int updateSchedule(Long scheduleId, String name, String title, String contents) {
        return jdbcTemplate.update("update set name = ?, title = ?, contents = ? where scheduleId = ?", name, title, contents, scheduleId);
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long scheduleId) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where scheduleId = ?", scheduleRowMapperV2(), scheduleId);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "요청한 일정을 조회할 수 없습니다"));
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){

        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("scheduleId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("contents")
                );
            }
        };
    }


    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("scheduleId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("contents")
                );
            }
        };
    }


}
