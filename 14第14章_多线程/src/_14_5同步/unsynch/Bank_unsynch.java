package _14_5Í¬²½.unsynch;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: Bank.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-16/ÏÂÎç05:15:33
 * Description: A bank with a number of bank accounts.
 */
public class Bank_unsynch {

	private final double[] accounts;
	
	/**
	 * Constructs the bank.
	 * @param n the number of account
	 * @param initialBalance the initial balance for each account
	 */
	public Bank_unsynch(int n,double initialBalance) {
		// TODO Auto-generated constructor stub
		accounts=new double[n];
		for(int i=0;i<accounts.length;i++){
			accounts[i]=initialBalance;
		}
	}
	
	/**
	 * Transfers money from one account to another.
	 * @param from the account to transfer from
	 * @param to the account to transfer to
	 * @param amount the amount to transfer
	 */
	public void transfer(int from, int to, double amount){
		if(accounts[from]<amount)return;
		System.out.print(Thread.currentThread());//
		accounts[from]-=amount;
		System.out.printf("%10.2f form %d to %d ",amount,from,to);//
		accounts[to]+=amount;
		System.out.printf("Total Balance: %10.2f \n",getTotalBaBalance());
		
	}

	/**
	 * Gets the sum of all account balance.
	 * @return the total balance
	 */
	private double getTotalBaBalance() {
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
