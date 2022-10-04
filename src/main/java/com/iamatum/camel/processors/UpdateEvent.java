package com.iamatum.camel.processors;

import com.iamatum.camel.domain.EventSchedule;
import com.iamatum.camel.repositories.VaccineRepository;
import com.iamatum.camel.services.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class UpdateEvent {

    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    private EventService eventService;

    public void updateLastRunDate(){

        LocalDate maxStatsDate = vaccineRepository.getMaxStatsDate();
        EventSchedule eventSchedule = new EventSchedule();
        eventSchedule.setLastRunDate(maxStatsDate);
        eventSchedule.setEvent("admissions_event");
        log.info("The max stats date on the file updated is {}",maxStatsDate);

        eventService.updateEventSchedule(eventSchedule);


    }



}
