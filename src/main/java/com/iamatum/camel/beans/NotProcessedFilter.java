package com.iamatum.camel.beans;

import com.iamatum.camel.model.RivmData;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
@Slf4j
public class NotProcessedFilter {


    public boolean notProcessed(Exchange exchange){

        RivmData rivmData = (RivmData) exchange.getIn().getBody();

        LocalDate lastRunDate = (LocalDate) exchange.getIn().getHeader("lastRunDate");
        LocalDate statsDate = rivmData.getStatsDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return statsDate.isAfter(lastRunDate);


    }
}
