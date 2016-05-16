/**
 * 
 */
package _14_4同步.unsynch;

import _14_4同步.synch2.Bank_synch;

/**
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: TransferRunnable.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-16/下午05:15:47
 * Description: A runnable that transfers money from an account to other accounts in a bank.
 */
public class TransferRunnable implements Runnable {

	private Bank_synch bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY=10;
	
	/**
	 * Constructs a transfer runnable.
	 * @param b the bank between whose account money is transferred
	 * @param from the account to transfer money from
	 * @param max the maximum amount of money in each transfer
	 */
	public TransferRunnable(Bank_synch b,int from,double max) {
		// TODO Auto-generated constructor stub
		bank=b;
		fromAccount=from;
		maxAmount=max;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
			int toAccount=(int)(bank.Size()*Math.random());
//			double amount=maxAmount*Math.random();
			double amount=maxAmount*Math.random()*1.0;
			bank.transfer(fromAccount, toAccount, amount);
			Thread.sleep((int)(DELAY*Math.random()));
				
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}
