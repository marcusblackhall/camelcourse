package com.iamatum.camel.repositories;

import com.iamatum.camel.domain.EventSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface EventScheduleRepository extends JpaRepository<EventSchedule,String> {


}
