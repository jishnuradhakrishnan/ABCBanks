package com.pseudo.util;

import java.util.LinkedList;
import java.util.Scanner;

public class CustomerHandler {
	private boolean tellerOneHaveCustomer = false;
	private boolean tellerTwoHaveCustomer = false;
	private LinkedList<String> tellerOneQueue = new LinkedList<String>();
	private LinkedList<String> tellerTwoQueue = new LinkedList<String>();
	private int tellerOneServedCustomers = 0;
	private int tellerTwoServedCustomers = 0;
	// 480 seconds
	long waitTime = 18000;

	public void startTransaction() throws Exception {
		long startTime = System.currentTimeMillis();
		Scanner in = new Scanner(System.in);

		// app will end after 480 seconds from start time
		long endTime = startTime + waitTime;

		while (System.currentTimeMillis() < endTime) {

			System.out.println("enter customer name ");
			String customerName = in.next();
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
				Thread t = new Thread(r1);
				t.start();
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

		System.out.println("Banking Time is over");
		System.out.println("teller one served " + tellerOneServedCustomers + " customers");
		System.out.println("teller two served " + tellerTwoServedCustomers + " customers");
		//closing the scanner resource
		in.close();
	}

	public void tellerOne(String customerName) throws InterruptedException {
		// this teller needs 1 second to serve her customer
		long tellerOneCustomerServiceTime = 3000 * 1;
		System.out.println("teller one Says: I have " + tellerOneQueue.size() + " customers");
		while (tellerOneHaveCustomer) {
		}
		tellerOneHaveCustomer = true;

		Thread.sleep(tellerOneCustomerServiceTime);
		System.out.println("customer " + customerName + " has left from teller one");

		tellerOneHaveCustomer = false;
		tellerOneQueue.remove(0);
		tellerOneServedCustomers++;

	}

	public void tellerTwo(String customerName) throws InterruptedException {
		// this teller needs 2 second to serve her customer
		long tellerOneCustomerServiceTime = 2000 * 1;
		System.out.println("teller two Says: I have " + tellerTwoQueue.size() + " customers");
		while (tellerTwoHaveCustomer) {
		}
		tellerTwoHaveCustomer = true;

		Thread.sleep(tellerOneCustomerServiceTime);
		System.out.println("customer " + customerName + " has left from teller one");

		tellerTwoHaveCustomer = false;
		tellerTwoQueue.remove(0);
		tellerTwoServedCustomers++;

	}

}
