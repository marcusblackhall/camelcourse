package com.iamatum.camel.mappers;

import com.iamatum.camel.domain.VaccinePerDagPerMunicipailty;
import com.iamatum.camel.model.RivmData;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")

public interface  RivmDataToVaccinePojo {


    VaccinePerDagPerMunicipailty rivmDataToVaccinePerDag(RivmData rivmData);
}
