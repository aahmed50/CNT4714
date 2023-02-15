/*  Name: Asma Ahmed  
     Course: CNT 4714 Spring 2023 
     Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking 
     Due Date: February 12, 2023 
*/ 

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.locks.Condition;

public class BankAccount implements BankBuffer{
	
	//lock to control mutually exclusive access to bank account
	private Lock accessLock = new ReentrantLock();
	private Condition canWithdraw = accessLock.newCondition(); //signal to waiting withdraw thread that deposit has been made
	private Condition canDeposit = accessLock.newCondition(); //signal to waiting deposit thread 
	
	//variables
	private int balance = 0;
	private static int transactionNumber = 0;
	private static int transactionsSinceAudit = 0;
	private static int auditCounter=0;

	boolean occupied = false;
	
	private static final int DEPOSIT_ALERT_LEVEL = 350;
	private static final int WITHDRAWAL_ALERT_LEVEL = 75;
	
	BankAccount(){
		accessLock = new ReentrantLock();
		canWithdraw = accessLock.newCondition();
		canDeposit = accessLock.newCondition();
	}//constructor

	//deposit into bank account
	@Override
	public void deposit(int amount, String threadName) {
		accessLock.lock(); //lock bank account
		
		try {

			while(occupied) {
				canDeposit.await();
			}
			
			//logging for flag
			if(amount >= DEPOSIT_ALERT_LEVEL) {
				flag(amount, threadName, "Deposit");
			}else {
				//make deposit 
				balance+=amount;
				transactionNumber++;
				auditCounter++;

				//display balance
				System.out.print(threadName+" deposits $"+amount);
				System.out.println("\t\t\t\t\t\t(+)  Balance is $"+balance+"\t\t\t\t\t\t\t\t" + transactionNumber);
			}
			
			canWithdraw.signalAll(); //signal all waiting to make withdrawal
		} catch(Exception exception) {
			System.out.println("Exception thrown depositing");
		} finally {
			accessLock.unlock();
		}//close finally
	}//close deposit

	//withdraw
	@Override
	public void withdraw(int amount, String threadName) {	
		accessLock.lock(); //lock bank account

		try {

			while(occupied) {
				canWithdraw.await();
			}
			
			if((balance - amount) < 0) {
				//negative balance
				System.out.println("\t\t (******) WITHDRAWAL BLOCKED - INSUFFICIENT FUNDS");
				flag(amount, threadName, "Withdraw"); //flag log

				canWithdraw.await();
			} else {
				balance -= amount;
				transactionNumber++;
				auditCounter++;
				System.out.print("\t\t\t\t" + threadName+" withdraws $"+ amount);
				System.out.println("\t\t\t\t\t\t(-)  Balance is $"+balance+"\t\t\t\t\t\t\t\t" + transactionNumber);

				//logging for flag
				if(amount >= WITHDRAWAL_ALERT_LEVEL) {
				//flag(amount, threadName, "Withdraw");
				}//close if
			}//close else
			canWithdraw.signalAll(); //signal all waiting to make withdrawal
		} catch(Exception exception) {
			System.out.println("Exception thrown withdrawing");
		} finally {
			accessLock.unlock();
		}//close finally
	} //close withdraw
	
	@Override
	public void audit(int i, String threadName) {
		
		transactionsSinceAudit = auditCounter; //counts number of transactions since last audit
		System.out.println("*******************************************************************************************************************\n");
		System.out.println("AUDITOR FINDS CURRENT ACCOUNT BALANCE TO BE: $" + balance + "\t\t NUMBER OF TRANSACTIONS SINCE LAST AUDIT: " + transactionsSinceAudit);
		System.out.println("\n*****************************************************************************************************************");
		
		auditCounter=0; //reset counter
	}//close audit

//AUXILIARY FUNCTIONS
	//log flagged transactions to avoid money laundering
		public void flag(int amount, String threadName, String transactionType) {
			//flag withdraw
			if(transactionType == "Withdraw") {
				System.out.println("\n * * * Flagged Transaction - Withdrawal Agent " + threadName + " Made A Withdrawal In Excess of $" + WITHDRAWAL_ALERT_LEVEL + ".00 USD - See Flagged Transaction Log \n");
				WriteTransactionLog(amount, threadName, transactionType);
			}
			//flag deposit
			else {
			System.out.println("\n * * * Flagged Transaction - Deposit Agent " + threadName + " Made A Deposit In Excess of $" + DEPOSIT_ALERT_LEVEL + ".00 USD - See Flagged Transaction Log \n");
			WriteTransactionLog(amount, threadName, transactionType);
			}
		}//close flag
		
		//log for flags
		public void WriteTransactionLog(int amount, String threadName, String transactionType) {
			FileWriter transactionFile;
			
			try {
				if(!occupied) {
					occupied = true;
					transactionFile = new FileWriter("transactions.txt", true);
					//print withdraw
					if (transactionType == "Withdraw") {
						transactionFile.append("    ");
						LocalDate date = LocalDate.now();
						LocalTime time = LocalTime.now();
						
						transactionFile.append(transactionType+" Agent "+ threadName + " issued " + transactionType + " of " + amount + ".00 at:");
						transactionFile.append(date.toString() + " ");
						transactionFile.append(time.toString());
						transactionFile.append("\n");
						transactionFile.close();
						occupied = false;
					}//close if
					//print deposit
					else  {
						transactionFile.append("    ");
						LocalDate date = LocalDate.now();
						LocalTime time = LocalTime.now();
						
						transactionFile.append(transactionType+" Agent "+ threadName + " issued " + transactionType + " of " + amount + ".00 at:");
						transactionFile.append(date.toString() + " ");
						transactionFile.append(time.toString());
						transactionFile.append("\n");
						transactionFile.close();
						occupied = false;
					}//close if
				}//close if
			} catch (IOException exception) {
				exception.printStackTrace();
			}//close catch
		}//close WriteTransactionLog
		
}//close BankAccount
