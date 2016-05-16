package _14_5Í¬²½.synch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: Bank_synch.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-16/ÏÂÎç10:47:15
 * Description: A bank with a number of bank accounts that uses locks for serializing access.
 */
public class Bank_synch {

	private final double[]accounts;
	private Lock bankLock;
	private Condition sufficientFuns;
	
	/**
	 * Constructs the bank.
	 * @param n the number of accounts
	 * @param initialBalance the initial balance for each account
	 */
	public Bank_synch(int n,double initialBalance){
		
		accounts=new double[n];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i]=initialBalance;
		}
		/**/
		bankLock=new ReentrantLock();
		sufficientFuns=bankLock.newCondition();
	}
	
	/**
	 * Transfer money from one account to another.
	 * @param from the account to transfer from
	 * @param to the account to transfer to
	 * @param amount the amount to transfer
	 * @throws InterruptedException 
	 */
	public void transfer(int from,int to, double amount) throws InterruptedException{
		
		try{

			bankLock.lock();/**/
			while(accounts[from]<amount){
				sufficientFuns.await();/**/
			}
			System.out.print(Thread.currentThread());//
			accounts[from]-=amount;
			System.out.printf("%10.2f form %d to %d ",amount,from,to);//
			accounts[to]+=amount;
			System.out.printf("Total Balance: %10.2f%n",getTotalBaBalance());
			
			sufficientFuns.notifyAll();/**/
		}finally{
			bankLock.unlock();/**/
		}
	}

	/**
	 * Gets the sum of all account balance.
	 * @return the total balance
	 */
	private double getTotalBaBalance() {
		// TODO Auto-generated method stub
		bankLock.lock();
		try{

			double sum=0;
			for (double a : accounts) {
				sum+=a;
			}
			return sum;
		
		}finally{
			bankLock.unlock();
		}
	}
	
	/**
	 * Gets the number of accounts in the bank.
	 * @return the number of accounts
	 */
	public int Size(){
		return accounts.length;
	}
}
