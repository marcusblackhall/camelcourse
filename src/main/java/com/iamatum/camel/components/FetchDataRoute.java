package com.iamatum.camel.components;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//Currently disabled
@Component
public class FetchDataRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:vaccinedata")
                .routeId("fetchDataId")
                .to("ahc:https://data.rivm.nl/covid-19/COVID-19_ziekenhuisopnames.csv");

        from("direct:saveData")
                .to("file:src/data/output?fileName=rivmData.csv");


        from("timer:rivmTimer?period=1000&repeat-Count=1")
                .routeId("rivmTimerId")
                .to("direct:vaccinedata")
                .to("direct:saveData");


    }
}
