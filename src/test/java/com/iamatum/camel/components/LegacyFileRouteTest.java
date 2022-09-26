package com.iamatum.camel.components;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
@UseAdviceWith
class LegacyFileRouteTest {

    @Autowired
    CamelContext camelContext;

    @EndpointInject("mock:result")
    protected MockEndpoint mockEndpoint;

    @Autowired
    ProducerTemplate producerTemplate;



    @Test
    void shouldReceiveInputFileFromMock() throws Exception {

        String expectedBody = "This is a test input file";

        mockEndpoint.expectedBodiesReceived(expectedBody);
        mockEndpoint.expectedMinimumMessageCount(1);

        AdviceWith.adviceWith(camelContext,"legacyRouteId",
                routerBuilder -> {
                    routerBuilder.replaceFromWith("direct:mockStart");
                    routerBuilder.weaveByToUri("file:*").replace().to(mockEndpoint);
                }
        );
        camelContext.start();
        producerTemplate.sendBody("direct:mockStart",expectedBody);

        mockEndpoint.assertIsSatisfied();


    }

}