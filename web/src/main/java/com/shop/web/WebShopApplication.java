package com.shop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.shop.domain"})
@EnableJpaRepositories(basePackages = {"com.shop.domain"})
@ComponentScan(basePackages = {"com.shop.web","com.shop.domain"})
public class WebShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebShopApplication.class, args);
	}
}
