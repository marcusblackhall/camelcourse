package com.iamatum.camel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

@Component
public class JaxBRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        JaxbDataFormat dataFormat = new JaxbDataFormat(true);
        dataFormat.setContextPath("com.iamatum.camel.jaxb");


        from("file:src/data/input?fileName=person.xml")
                .routeId("jaxbrouteId")
                .unmarshal(dataFormat)
                .log(LoggingLevel.INFO, "${body}")
                .to("activemq:queue:personQueue?exchangePattern=InOnly");

        from("activemq:queue:personQueue")
                .marshal(dataFormat)
                .log(LoggingLevel.INFO, "${body}");


    }
}
