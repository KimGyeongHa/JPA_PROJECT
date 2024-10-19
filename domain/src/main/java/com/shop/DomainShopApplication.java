package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.shop.web","com.shop.domain"})
public class DomainShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(DomainShopApplication.class, args);
	}
}
