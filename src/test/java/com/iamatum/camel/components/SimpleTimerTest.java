package com.iamatum.camel.components;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@CamelSpringBootTest
@SpringBootTest
@UseAdviceWith
class SimpleTimerTest {

    @Autowired
    CamelContext camelContext;

    @EndpointInject("mock:result")
    protected MockEndpoint mockEndpoint;

    @Test
    void configure() throws Exception {

        String expectedBody = "Howdy Marcus";
        mockEndpoint.expectedBodiesReceived(expectedBody);
        mockEndpoint.expectedMinimumMessageCount(1);

        AdviceWith.adviceWith(camelContext,"simpleTimerId",
                routerBuilder -> routerBuilder.weaveAddLast().to(mockEndpoint)
                );
        camelContext.start();

        mockEndpoint.assertIsSatisfied();

    }
}