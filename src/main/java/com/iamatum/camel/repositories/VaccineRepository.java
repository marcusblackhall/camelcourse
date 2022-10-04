package com.iamatum.camel.repositories;

import com.iamatum.camel.domain.VaccinePerDagPerMunicipailty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface VaccineRepository extends JpaRepository<VaccinePerDagPerMunicipailty,Long> {

    public LocalDate getMaxStatsDate ();
}


