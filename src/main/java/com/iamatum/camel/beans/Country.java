package com.iamatum.camel.beans;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NamedQuery(name ="findAllCountries",query = "select c from Country c")
@NamedNativeQuery(name = "deleteById" ,query="delete from country where iso = ?")
public class Country implements Serializable {

    @Id
    private String iso;
    @Column(name="country_name")
    private String  countryName;
    private String capital;
}
