package com.iamatum.camel.aggregates;

import com.iamatum.camel.model.RivmData;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CollectInListStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        List<Object> list;
        if (oldExchange == null) {
            list = new ArrayList<>();
            Object newBody = newExchange.getIn().getBody();
            list.add(newBody);
            newExchange.getIn().setBody(list);
            return newExchange;
        }
        List oldBody =  (List)oldExchange.getIn().getBody();
        Object newBody = newExchange.getIn().getBody(Object.class);
        oldBody.add(newBody);
        oldExchange.getIn().setBody(oldBody);

        return oldExchange;


    }
}
