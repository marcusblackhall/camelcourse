package com.iamatum.camel.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE)

class EventScheduleRepositoryTest {

    @Autowired
    VaccineRepository vaccineRepository;

    @Test
    void shouldGetMaxStatsDate(){
        vaccineRepository.getMaxStatsDate();
    }

}