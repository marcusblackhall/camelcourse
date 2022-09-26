package com.iamatum.camel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReceiveMsgFromQueueRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("activemq:queue:countryQueue")
                .routeId("receiveQueueId")
                .log(LoggingLevel.INFO,"recieived message ${body}");

    }
}
