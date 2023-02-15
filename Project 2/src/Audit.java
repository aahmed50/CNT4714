/*  Name: Asma Ahmed  
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 15, 2023 
*/ 

import java.util.Random;

//audit class run method
public class Audit implements Runnable{

	private static Random sleeptime = new Random();
	private BankAccount Account; //reference to shared bank account object
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
				
			}catch(InterruptedException exception) {
				exception.printStackTrace();
			}//close catch
		}//close while
	}//close run
}//close class Audit