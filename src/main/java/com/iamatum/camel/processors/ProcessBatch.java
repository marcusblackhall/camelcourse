package com.iamatum.camel.processors;

import com.iamatum.camel.domain.VaccinePerDagPerMunicipailty;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Component
public class ProcessBatch implements Processor {

    private final String ADMISSION_SQL = """
            INSERT INTO public.admissions(
            version, date_report, stats_date, municipality_code, municipality_name, region_code, region_name, no_notifications, no_admissions)
            VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;


    public ProcessBatch(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void process(Exchange exchange) {

        java.util.List<VaccinePerDagPerMunicipailty> data = (java.util.List<VaccinePerDagPerMunicipailty>) exchange.getIn().getBody();

        BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {

                VaccinePerDagPerMunicipailty rec = data.get(i);
                ps.setInt(1, rec.getVersion());
                ps.setDate(2, rec.getDateReport());
                ps.setDate(3, rec.getStatsDate());
                ps.setString(4, rec.getMunicipalityCode());
                ps.setString(5, rec.getMunicipalityName());
                ps.setString(6, rec.getRegionCode());
                ps.setString(7, rec.getRegionName());
                ps.setInt(8, rec.getNoNotifications());
                ps.setInt(9, rec.getNoAdmissions());

            }

            @Override
            public int getBatchSize() {
                return data.size();
            }
        };

        jdbcTemplate.batchUpdate(ADMISSION_SQL, batchPreparedStatementSetter);


    }
}
