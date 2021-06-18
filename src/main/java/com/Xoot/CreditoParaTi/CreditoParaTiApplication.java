package com.Xoot.CreditoParaTi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CreditoParaTiApplication implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(CreditoParaTiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = encoder.encode("S3gur1d4d");
		System.out.println(password); 
	}

}
