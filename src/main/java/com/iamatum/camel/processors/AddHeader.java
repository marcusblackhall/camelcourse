package com.iamatum.camel.processors;

import com.iamatum.camel.domain.EventSchedule;
import com.iamatum.camel.services.EventService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class AddHeader implements Processor {

    @Autowired
    EventService eventService;

    @Override
    public void process(Exchange exchange) throws Exception {

        EventSchedule admissionsEvent = eventService.getEventSchedule("admissions_event");

        LocalDate lastRunDate = admissionsEvent.getLastRunDate();
        exchange.getIn().setHeader("lastRunDate",lastRunDate);

    }
}
