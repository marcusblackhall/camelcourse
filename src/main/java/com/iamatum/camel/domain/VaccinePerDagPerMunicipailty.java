package com.iamatum.camel.domain;

import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "admissions")

@NamedQuery(name = "VaccinePerDagPerMunicipailty.getMaxStatsDate", query = "select max(v.statsDate) from VaccinePerDagPerMunicipailty v")
public class VaccinePerDagPerMunicipailty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer version;
    private Date dateReport;
    private Date statsDate;
    private String municipalityCode;
    private String municipalityName;
    private String regionCode;
    private String regionName;
    @Column(name="no_notifications")
    private Integer noNotifications;
    private Integer noAdmissions;


}
