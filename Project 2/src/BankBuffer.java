/*  Name: Asma Ahmed  
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 15, 2023 
*/ 

public abstract interface BankBuffer {
	
	public void deposit(int amount, String threadName); //write
	
	public void withdraw(int amount, String threadName); //read
	
	public void audit(int i, String threadName); //print
}
