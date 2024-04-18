package com.finance.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.finance.app")
public class FinanceApplicationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplicationProjectApplication.class, args);
	}

}
