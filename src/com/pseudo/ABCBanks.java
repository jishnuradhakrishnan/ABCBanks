package com.pseudo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pseudo.util.CustomerHandler;

@SpringBootApplication
public class ABCBanks implements CommandLineRunner {
	/*this is a spring boot application
	 * a bank named ABC have 2 tellers amy and ana. 
	 * 
	 */
	public static void main(String[] args) {
		//strting the spring boot application
		SpringApplication.run(ABCBanks.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// bank needs atleast 1 second time to start their transaction
		Thread.sleep(1000);
		//calling customerHandler util class
		CustomerHandler customerHandler = new CustomerHandler();
		//starting todays transaction
		customerHandler.startTransaction();

		//the system exists after 480 seconds, it wont care about how many people are there in the queue
		System.exit(0);
	}

}
