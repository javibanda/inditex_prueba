package com.inditex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ZaraChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaraChallengeApplication.class, args);
	}

}