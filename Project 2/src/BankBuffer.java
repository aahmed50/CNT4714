/*  Name: Asma Ahmed  
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 12, 2023 
*/ 

public abstract interface BankBuffer {
	
	//thread name, deposit amount
	public void deposit(int amount, String threadName); //write
	
	//thread name, withdrawal amount
	public void withdraw(int amount, String threadName); //read
	
	public void audit();
}
