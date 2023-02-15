/*  Name: Asma Ahmed  
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 12, 2023 
*/ 

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//main driver class: creates threads, defines bank account

public class BankDriver {

	public static final int MAX_AGENTS = 16; //max agents; threads for the thread pool
	
	//main method
	public static void main(String[] args) {
		
		//initialize simulator
		ExecutorService application = Executors.newFixedThreadPool( MAX_AGENTS ); //executor object
		BankAccount Account = new BankAccount(); //create bank account

		//output
				System.out.println("Deposit Agents\t\t\t   Withdrawal Agents\t\t\t   Balance");		
				System.out.println("--------------\t\t\t   -----------------\t\t\t   -------");

		//10 withdrawal threads, 5 deposit threads, 1 auditor thread
		//initialize agents 
		Deposit T1 = new Deposit(Account, "Thread1");
		Deposit T2 = new Deposit(Account, "Thread2");
		Deposit T3 = new Deposit(Account, "Thread3");
		Deposit T4 = new Deposit(Account, "Thread4");
		Deposit T5 = new Deposit(Account, "Thread5");
		
		Withdraw T6 = new Withdraw(Account, "Thread6");
		Withdraw T7 = new Withdraw(Account, "Thread7");
		Withdraw T8 = new Withdraw(Account, "Thread8");
		Withdraw T9 = new Withdraw(Account, "Thread9");
		Withdraw T10 = new Withdraw(Account, "Thread10");
		Withdraw T11 = new Withdraw(Account, "Thread11");
		Withdraw T12 = new Withdraw(Account, "Thread12");
		Withdraw T13 = new Withdraw(Account, "Thread13");
		Withdraw T14 = new Withdraw(Account, "Thread14");
		Withdraw T15 = new Withdraw(Account, "Thread15");
		
		Audit T16 = new Audit(Account, "AUDITOR");
		
		try {
			application.execute(T1);
			application.execute(T2);
			application.execute(T3);
			application.execute(T4);
			application.execute(T5);
			application.execute(T6);
			application.execute(T7);
			application.execute(T8);
			application.execute(T9);
			application.execute(T10);
			application.execute(T11);
			application.execute(T12);
			application.execute(T13);
			application.execute(T14);
			application.execute(T15);
			application.execute(T16);
		} catch (Exception exception) {
			exception.printStackTrace();
		}//end catch

		application.shutdown();
	}//close main
}//close BankDriver