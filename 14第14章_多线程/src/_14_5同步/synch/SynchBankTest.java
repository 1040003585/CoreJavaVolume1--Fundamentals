package _14_5Í¬²½.synch;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: UnsynchBankTest.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-16/ÏÂÎç05:07:36
 * Description: This program shows data corruption when multiple threads access a data structure.
 */
public class SynchBankTest {

	public static final int NACOUNTS=3;
	public static final double INITIAL_BALANCE=1000;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank_synch bank = new Bank_synch(NACOUNTS,INITIAL_BALANCE);
		
		for (int f = 0; f < NACOUNTS; f++) {
			TransferRunnable r = new TransferRunnable(bank,f,INITIAL_BALANCE);
			Thread thread = new Thread(r);
			thread.start();
		}
	}

}
