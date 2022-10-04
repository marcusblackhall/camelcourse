package com.iamatum.camel.components;

import com.iamatum.camel.domain.Country;
import com.iamatum.camel.beans.InboundBean;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

import java.net.ConnectException;

import static net.bytebuddy.implementation.MethodDelegation.to;

/**
 * Post a request
 * Send the message to activemq
 * Send to the Jpa component
 *
 * seda is a blocking queue
 * wiretap --- separate thead
 */
@Component
public class RestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(JMSException.class, ConnectException.class)
                .handled(true)
                        .log(LoggingLevel.INFO,"Error handled gracefully");

        restConfiguration()
                .component("jetty")
                .host("0.0.0.0")
                .port(8080)
                .bindingMode(RestBindingMode.json)
                .enableCORS(true);

        Predicate isCapitalTokyo = header("capital").isEqualTo("Tokyo");

        rest("masterclass").produces("application/json")
                .post("country")
                .type(Country.class)
                .route().routeId("restRouteId")
                .bean(new InboundBean())

                .choice()
                .when(isCapitalTokyo)
                .otherwise()
                .log(LoggingLevel.INFO,"It was not tokyo")
                .log(LoggingLevel.INFO,"Predicate says you got tokyo")
                .end()

                .log(LoggingLevel.INFO, "${body}")
//                .convertBodyTo(String.class)
//                .to("file:src/data/output?fileName=country.json");

//                .to("activemq:queue:countryQueue?exchangePattern=InOnly")
//                .to("jpa:" + Country.class.getName());
                .log("sending to Db endpoint")
        .wireTap("seda:toDb")
                .log("sending to activemq endpoint")
                .to("direct:toActiveMq");

        from("seda:toDb").routeId("toDbId")

                .log(LoggingLevel.INFO, "in DB enpoint ...")
                .to("jpa:" + Country.class.getName());

        from("direct:toActiveMq")
                .routeId("toActiveMqId")
                .log(LoggingLevel.INFO,"in activemq endpoint ...")
                .to("activemq:queue:countryQueue?exchangePattern=InOnly");

    }
}
