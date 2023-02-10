/*  Name: Asma Ahmed  
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 12, 2023 
*/ 

import java.util.Random;

public class Withdraw implements Runnable{

	private static int MAX_WITHDRAWAL = 99;
	private static Random generator = new Random(); //generate random number
	//private static Random sleepTime = new Random(); //once a depositor agent has deposited into the account, put it to sleep for few milliseconds
	
	private BankAccount Account; //reference to shared bank account object
	private String threadName;
	
	public Withdraw(BankAccount sharedAccount, String name) {
		Account = sharedAccount;
		threadName = name;
	}//close constructor
	
	@Override
	public void run() {
		//withdraws money from bank account
		while(true) {
			try {
				Account.withdraw(generator.nextInt(MAX_WITHDRAWAL-1+1)+1, threadName);
				
				//sleep for random amount of milliseconds
				Thread.sleep(generator.nextInt(140-1+1)); //sleep thread			
				Thread.yield(); //avoid monopolizing CPU
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}//close catch
		}//close while loop
		
	}//close run
	
}//close class Withdraw
