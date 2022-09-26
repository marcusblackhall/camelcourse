package com.iamatum.camel.components;

import com.iamatum.camel.beans.Country;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import static net.bytebuddy.implementation.MethodDelegation.to;

@Component
public class RestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("jetty")
                .host("0.0.0.0")
                .port(8080)
                .bindingMode(RestBindingMode.json)
                .enableCORS(true);

        rest("masterclass").produces("application/json")
                .post("country")
                .type(Country.class)
                .route().routeId("restRouteId")
                .log(LoggingLevel.INFO, "${body}")
//                .convertBodyTo(String.class)
//                .to("file:src/data/output?fileName=country.json");

//                .to("activemq:queue:countryQueue?exchangePattern=InOnly")
//                .to("jpa:" + Country.class.getName());

        .to("direct:toDb")
                .to("direct:toActiveMq");

        from("direct:toDb").routeId("toDbId").to("jpa:" + Country.class.getName());

        from("direct:toActiveMq")
                .routeId("toActiveMqId")
                .to("activemq:queue:countryQueue?exchangePattern=InOnly");

    }
}
