package com.iamatum.camel.components;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LegacyFileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:src/data/input?fileName=inputFile.txt")
                .routeId("legacyRouteId")
                .process(exchange -> {

                  log.info("The data in file is {}" ,  exchange.getIn().getBody(String.class));

                })
                .to("file:src/data/output?fileName=outputFile.txt");


    }
}
