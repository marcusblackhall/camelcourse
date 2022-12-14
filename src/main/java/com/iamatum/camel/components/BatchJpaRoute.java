package com.iamatum.camel.components;

import com.iamatum.camel.domain.Country;
import com.iamatum.camel.processors.InboundProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatchJpaRoute extends RouteBuilder {

    @Autowired
    InboundProcessor inboundProcessor;
    @Override
    public void configure() throws Exception {

        from("timer:readDb?period=1000")
                .routeId("RemoveRouteId")
                .to("jpa:" + Country.class.getName() + "?namedQuery=findAllCountries")
                .split(body())
                .process(inboundProcessor)
                .log(LoggingLevel.INFO,"${body} " + "${header.deleteIso}" )
                .convertBodyTo(String.class)
                .to("file:src/data/output?fileName=outputFile.csv&fileExist=append&appendChars=\\n")
                .log(LoggingLevel.INFO,"deleting ${header.deleteIso}")
                .toD("jpa:" + Country.class.getName() + "?nativeQuery=DELETE FROM country where iso = '${header.deleteIso}'&useExecuteUpdate=true" )
                .end();


    }
}
