package com.iamatum.camel.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CsvProcessor implements Processor {

    Integer count = 0;

    @Override
    public void process(Exchange exchange) throws Exception {

        if (exchange.getIn().getBody() instanceof List) {
            List items = exchange.getIn().getBody(List.class);
            count += items.size();
            log.info("No.of items {} {}", items.size(), count);


        }


    }
}
