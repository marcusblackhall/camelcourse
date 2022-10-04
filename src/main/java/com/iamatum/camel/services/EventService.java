package com.iamatum.camel.services;

import com.iamatum.camel.domain.EventSchedule;
import com.iamatum.camel.repositories.EventDao;
import com.iamatum.camel.repositories.EventScheduleRepository;
import org.springframework.stereotype.Component;

@Component
public class EventService {

    private final EventDao eventDao;

    private final EventScheduleRepository eventScheduleRepository;

    public EventService(EventDao eventDao, EventScheduleRepository eventScheduleRepository) {
        this.eventDao = eventDao;
        this.eventScheduleRepository = eventScheduleRepository;
    }


    public EventSchedule getEventSchedule(String eventSchedule){

        return eventDao.getEventSchedule(eventSchedule);

    }

    public EventSchedule updateEventSchedule(EventSchedule event){

        return eventScheduleRepository.save(event);

    }


}
