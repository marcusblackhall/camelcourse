package com.iamatum.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.iamatum.camel.beans")
public class CamelFeaturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelFeaturesApplication.class, args);
	}

}
