package com.iamatum.camel.processors;

import com.iamatum.camel.domain.Country;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InboundProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Country country  = exchange.getIn().getBody(Country.class);
        log.info("Processing record ISO {}",country.getIso());

        exchange.getIn().setHeader("deleteIso",country.getIso());


    }
}
