/*  Name: Asma Ahmed  
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 15, 2023 
*/ 

import java.util.Random;

//deposit class run method
public class Deposit implements Runnable{
	
	private static int MAX_DEPOSIT = 500; //deposits  are  made  in amounts ranging from $1 to $500
	private static Random generator = new Random(); //generate random number
	//private static Random sleepTime = new Random(); //once a depositor agent has deposited into the account, put it to sleep for few milliseconds
	
	private BankAccount Account; //reference to shared bank account object
	private String threadName;
	
	public Deposit(BankAccount sharedAccount, String name) {
		Account = sharedAccount;
		threadName = name;
	}//close constructor
	
	//override Runnable
	@Override
	public void run() {
		//deposits money into bank account
		while(true) {
			try {
				
				Account.deposit(generator.nextInt(MAX_DEPOSIT-1+1)+1, threadName); //random deposit amount
				//sleep for random amount of milliseconds
				Thread.sleep(generator.nextInt(300)+1); //sleep thread		
				
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}//close catch
		}//close while loop
	}//close run
}//close class Deposit
