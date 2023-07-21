package com.sinulingga.train;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TrainApplication implements CommandLineRunner {
	private final Logger LOG = LoggerFactory.getLogger(TrainApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(TrainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

	public void initBookList() {
	}
}
