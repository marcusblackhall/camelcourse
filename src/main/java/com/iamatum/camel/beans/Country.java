package com.iamatum.camel.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Data
@Entity
@NamedQuery(name ="findAllCountries",query = "select c from Country c")
@NamedNativeQuery(name = "deleteById" ,query="delete from country where iso = ?")
public class Country implements Serializable {

    @Id
    private String iso;
    private String  name;
    private String capital;
}
