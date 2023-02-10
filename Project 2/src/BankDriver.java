/*  Name: Asma Ahmed  
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 12, 2023 
*/ 

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//main driver class: creates threads, defines bank account

public class BankDriver {

	public static final int MAX_AGENTS = 16; //max agents; threads for the thread pool
	
	//main method
	public static void main(String[] args) {
		
		//initialize simulator
		ExecutorService application = Executors.newFixedThreadPool( MAX_AGENTS ); //executor object
		
		//create bank account
		//BankAccount Account = new BankAccount();
		
		//output
		System.out.println("Deposit Agents\t\t\t   Withdrawal Agents\t\t\t   Balance");		
		System.out.println("--------------\t\t\t   -----------------\t\t\t   -------");

		

	}//close main
}//close BankDriver
