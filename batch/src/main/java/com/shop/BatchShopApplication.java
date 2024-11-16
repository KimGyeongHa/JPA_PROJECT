package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.shop.batch","com.shop.domain"})
public class BatchShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(BatchShopApplication.class, args);
	}
}
