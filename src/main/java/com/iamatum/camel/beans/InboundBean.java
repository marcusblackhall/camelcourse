package com.iamatum.camel.beans;

import com.iamatum.camel.domain.Country;
import org.apache.camel.Exchange;

public class InboundBean {

    public void validate (Exchange exchange){

        Country body = exchange.getIn().getBody(Country.class);

        exchange.getIn().setHeader("capital",body.getCapital());


    }
}
