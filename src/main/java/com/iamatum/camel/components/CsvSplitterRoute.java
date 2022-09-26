package com.iamatum.camel.components;

import com.iamatum.camel.beans.Country;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.beanio.BeanIODataFormat;
import org.springframework.stereotype.Component;

@Component
public class CsvSplitterRoute extends RouteBuilder {

    BeanIODataFormat beanIODataFormat = new BeanIODataFormat("inboundMapping.xml","countryStream");
    @Override
    public void configure() throws Exception {

        from("file:src/data/input?fileName=input.csv")
                .routeId("csvSplitterRouteId")
                .split(body().tokenize("\n",1,true))
                .unmarshal(beanIODataFormat)
                .process(exchange -> {
                    Country country = exchange.getIn().getBody(Country.class);
                    log.info("Process exchange {}", country);
                    exchange.getIn().setBody(country.toString());

                     })
                   .to("file:src/data/output?fileName=output.csv&fileExist=append&appendChars=\\n")
                .end();


    }
}
