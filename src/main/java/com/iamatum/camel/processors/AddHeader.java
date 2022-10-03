package com.iamatum.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AddHeader implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        LocalDate lastRunDate = LocalDate.now().minusDays(3000);
        exchange.getIn().setHeader("lastRunDate",lastRunDate);

    }
}
