package com.iamatum.camel.domain;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event_schedule")
@Data

public class EventSchedule {

    @Id
    private String event;

    @Column(name="last_run_date")
    private LocalDate lastRunDate;
}
