package com.pseudo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pseudo.util.CustomerHandler;

@SpringBootApplication
public class ABCBanks implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ABCBanks.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// lets wait one second
		Thread.sleep(1000);
		CustomerHandler customerHandler = new CustomerHandler();
		customerHandler.startTransaction();

	}

}
