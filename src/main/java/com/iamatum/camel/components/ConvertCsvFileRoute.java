package com.iamatum.camel.components;


import com.iamatum.camel.beans.NotProcessedFilter;
import com.iamatum.camel.processors.AddHeader;
import com.iamatum.camel.processors.ProcessBatch;
import com.iamatum.camel.aggregates.CollectInListStrategy;
import com.iamatum.camel.mappers.RivmDataToVaccinePojo;
import com.iamatum.camel.processors.CsvProcessor;
import com.iamatum.camel.processors.UpdateEvent;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

// use bindy
@Component
public class ConvertCsvFileRoute extends RouteBuilder {

    @Autowired
    ProcessBatch processBatch;
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    NotProcessedFilter notProcessedData;

    @Autowired
    AddHeader addHeader;

    @Autowired
    UpdateEvent updateEvent;

    @Autowired
    CsvProcessor csvProcessor;

    @Autowired
    RivmDataToVaccinePojo rivmDataToVaccinePojo;
    @Override
    public void configure() throws Exception {


        CollectInListStrategy collectInListStrategy = new CollectInListStrategy();

        BindyCsvDataFormat bindy = new BindyCsvDataFormat(com.iamatum.camel.model.RivmData.class);
        CsvDataFormat csvDataFormat = new CsvDataFormat();
        csvDataFormat.setSkipHeaderRecord("true");


        from("file:src/data/input?fileName=rivmData.csv")
                .routeId("rivmCsvSplitId")
                .onCompletion()
                .bean(updateEvent,"updateLastRunDate")
                .log(LoggingLevel.INFO,"Everything is completed")
                .end()
                .process(addHeader)
                .unmarshal(bindy)
                .split(body())
                .streaming()
                .filter().method(notProcessedData,"notProcessed")
                .bean(rivmDataToVaccinePojo)
                .aggregate(constant(true),collectInListStrategy)
                .completionSize(100)
                .completionTimeout(500)
                .to("seda:handleAdmissions")
                .end();

        from("seda:handleAdmissions?concurrentConsumers=5")
                .routeId("storeToDBId")
                .process(processBatch);

    }
}
