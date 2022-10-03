package com.iamatum.camel.model;


import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.util.Date;

@CsvRecord(separator = ";",skipFirstLine = true)
@Data
public class RivmData {

    @DataField(pos = 1)
    private Integer version;
    @DataField(pos = 2 ,pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date dateReport;
    @DataField(pos = 3 ,pattern="yyyy-MM-dd")
    private Date statsDate;
    @DataField(pos = 4)
    private String municipalityCode;
    @DataField(pos = 5)
    private String municipalityName;
    @DataField(pos = 6)
    private String regionCode;
    @DataField(pos = 7)
    private String regionName;
    @DataField(pos = 8)
    private Integer noNotifications;
    @DataField(pos = 9)
    private Integer noAdmissions;
}
