package com.pseudo.util;

import java.util.LinkedList;
import java.util.Scanner;

public class CustomerHandler {
	//flag to check whether tellers are busy or not
	private boolean tellerOneHaveCustomer = false;
	private boolean tellerTwoHaveCustomer = false;
	
	//adding customers into queue
	private LinkedList<String> tellerOneQueue = new LinkedList<String>();
	private LinkedList<String> tellerTwoQueue = new LinkedList<String>();
	
	//this indicates the number of customers served by both tellers
	private int tellerOneServedCustomers = 0;
	private int tellerTwoServedCustomers = 0;
	
	// in one day bank will only work for 480 seconds
	long waitTime = 480000;

	public void startTransaction() throws Exception {
		long startTime = System.currentTimeMillis();
		Scanner in = new Scanner(System.in);

		// bank will close after 480 seconds from startTime
		long endTime = startTime + waitTime;

		while (System.currentTimeMillis() < endTime) {

			//in this loop each and ever customer arrived is added to queue
			System.out.println("enter customer name ");
			String customerName = in.next();
			
			//if amy have more than 5 customers in her queue she will pass new customers to ana
			if (tellerOneQueue.size() < 5) {
				System.out.println("customer " + customerName + " is on teller one");
				tellerOneQueue.add(customerName);
				Runnable r1 = new Runnable() {

					@Override
					public void run() {
						try {
							tellerOne(customerName);
						} catch (InterruptedException e) {
							System.out.println(e.getLocalizedMessage());
						}
					}
				};
				//all customers is considered as a seperate thread(sorry thats the rule!!)
				Thread t = new Thread(r1);
				t.start();
				//else is for ana
			} else {
				System.out.println("customer " + customerName + " is on teller two");
				tellerTwoQueue.add(customerName);
				Runnable r1 = new Runnable() {

					@Override
					public void run() {
						try {
							tellerTwo(customerName);
						} catch (InterruptedException e) {
							System.out.println(e.getLocalizedMessage());
						}
					}
				};
				Thread t = new Thread(r1);
				t.start();
			}
		}

		//after transaction closed it will print the report as below
		System.out.println("Banking Time is over");
		System.out.println("teller one served " + tellerOneServedCustomers + " customers");
		System.out.println("teller two served " + tellerTwoServedCustomers + " customers");
		//closing the scanner resource
		in.close();
	}

	public void tellerOne(String customerName) throws InterruptedException {
		// this teller needs 1 second to serve her customer
		long tellerOneCustomerServiceTime = 1000 * 1;
		System.out.println("teller one Says: I have " + tellerOneQueue.size() + " customers");
		
		//this is an infinite loop, it wil end if the flag set false....
		while (tellerOneHaveCustomer) {
		}
		tellerOneHaveCustomer = true;

		//this is the time for serving her customer
		Thread.sleep(tellerOneCustomerServiceTime);
		//after customer left the counter amy will say as follows
		System.out.println("customer " + customerName + " has left from teller one");

		//she is free now
		tellerOneHaveCustomer = false;
		//removing the first customer standing at front of the queue
		tellerOneQueue.remove(0);
		tellerOneServedCustomers++;

	}

	public void tellerTwo(String customerName) throws InterruptedException {
		// this teller needs 2 second to serve her customer
		long tellerOneCustomerServiceTime = 2000 * 1;
		System.out.println("teller two Says: I have " + tellerTwoQueue.size() + " customers");
		
		//this is an infinite loop, it wil end if the flag set false....
		while (tellerTwoHaveCustomer) {
		}
		tellerTwoHaveCustomer = true;

		//this is the time for serving her customer
		Thread.sleep(tellerOneCustomerServiceTime);
		//after customer left the counter ana will say as follows
		System.out.println("customer " + customerName + " has left from teller two");

		//she is free now
		tellerTwoHaveCustomer = false;
		//removing the first customer standing at front of the queue
		tellerTwoQueue.remove(0);
		tellerTwoServedCustomers++;

	}

}
