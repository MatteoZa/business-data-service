package com.aerztekasse.business.data.service;

import com.aerztekasse.business.data.service.util.DataImport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		DataImport.importPlacesData();
		SpringApplication.run(Application.class, args);
	}

}
