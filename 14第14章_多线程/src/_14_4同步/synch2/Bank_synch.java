package _14_4Í¬²½.synch2;


/**
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: Bank_sych2.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-17/ÉÏÎç12:25:53
 * Description: A bank with a number of bank accounts that uses synchronization primitives.
 */
public class Bank_synch {

	private final double[]accounts;
	
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

	}
	
	/**
	 * Transfer money from one account to another.
	 * @param from the account to transfer from
	 * @param to the account to transfer to
	 * @param amount the amount to transfer
	 * @throws InterruptedException 
	 */
	public synchronized void transfer(int from,int to, double amount) throws InterruptedException{
		
		
			while(accounts[from]<amount){
				wait();/**/
			}
			System.out.print(Thread.currentThread());//
			accounts[from]-=amount;
			System.out.printf("%10.2f form %d to %d ",amount,from,to);//
			accounts[to]+=amount;
			System.out.printf("Total Balance: %10.2f%n",getTotalBaBalance());
			
			notifyAll();/**/
		
	}

	/**
	 * Gets the sum of all account balance.
	 * @return the total balance
	 */
	private synchronized double getTotalBaBalance() {
		// TODO Auto-generated method stub
		
			double sum=0;
			for (double a : accounts) {
				sum+=a;
			}
			return sum;
		
	}
	
	/**
	 * Gets the number of accounts in the bank.
	 * @return the number of accounts
	 */
	public int Size(){
		return accounts.length;
	}
}
