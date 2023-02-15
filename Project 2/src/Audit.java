/*  Name: Asma Ahmed  
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 12, 2023 
*/ 

import java.util.Random;

public class Audit implements Runnable{

	private static Random sleeptime = new Random();
	private BankAccount Account; //reference to shared bank account object
	//private BankAccount Balance;
	private String threadName;
	
	public Audit(BankAccount sharedAccount, String name) {
		Account = sharedAccount;
		threadName = name;
	}//close constructor
	
	@Override
	public void run() {
		while(true) {
			try {
				Account.audit(sleeptime.nextInt(), threadName);
				//sleep random time then run audit
				Thread.sleep(sleeptime.nextInt(300)); //sleep thread
				//access bank account to get balance
				/*
				System.out.println("*********************************\n");
				System.out.println("AUDITOR FINDS CURRENT ACCOUNT BALANCE TO BE: $" + Balance + "\t\t NUMBER OF TRANSACTIONS SINCE LAST AUDIT IS ");
				System.out.println("\n*********************************");

				*/ 
				//sleep more
			}catch(InterruptedException exception) {
				exception.printStackTrace();
			}//close catch
		}//close while
	}//close run
				
			
}//close class Audit
