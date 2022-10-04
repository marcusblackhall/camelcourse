package com.iamatum.camel.repositories;

import com.iamatum.camel.domain.EventSchedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class EventDao {

    private final JdbcTemplate jdbcTemplate;

    public EventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public EventSchedule getEventSchedule(String eventSchedule) {

        return jdbcTemplate.query("select * from event_schedule", extractor());


    }

    ResultSetExtractor<EventSchedule> extractor() {

        return (rs) -> {
            while (rs.next()) {
                EventSchedule eventSchedule = new EventSchedule();
                eventSchedule.setEvent(rs.getString("event"));
                java.sql.Date last_run_date = rs.getDate("last_run_date");

                if (last_run_date != null) {
                    LocalDate localDateTime = last_run_date.toLocalDate();

                    eventSchedule.setLastRunDate(localDateTime);
                }
                return eventSchedule;
            }
            return null;
        };

    }
}
