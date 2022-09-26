package com.iamatum.camel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleTimer extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:simpletimer?period=2000&repeat-Count=1")
                .routeId("simpleTimerId")
                .setBody(constant("Howdy Marcus"))
                .log(LoggingLevel.INFO,"${body}");

    }
}
