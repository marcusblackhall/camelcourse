package com.iamatum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = {"com.iamatum.camel.domain"})
public class CamelFeaturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelFeaturesApplication.class, args);
	}

}
