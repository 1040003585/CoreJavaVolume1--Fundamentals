package _14_5ͬ��.synch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: Bank_synch.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-16/����10:47:15
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
				System.out.println("await.....");
			}
			System.out.print(Thread.currentThread());//
			accounts[from]-=amount;
			System.out.printf("%10.2f form %d to %d ",amount,from,to);//
			accounts[to]+=amount;
			System.out.printf("Total Balance: %10.2f%n",getTotalBaBalance());
			//http://www.cnblogs.com/alphablox/archive/2013/01/20/2868479.html
			sufficientFuns.notifyAll();
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


/**http://www.cnblogs.com/alphablox/archive/2013/01/20/2868479.html
 * 
 
Lock��await/singal �� Object��wait/notify ������

��ʹ��Lock֮ǰ�����Ƕ�ʹ��Object ��wait��notifyʵ��ͬ���ġ�������˵��һ��producer��consumer��consumer����û�ж����ˣ��ȴ���produer���ɶ����ˣ����ѡ�

�߳�consumer	 �߳�producer
synchronize(obj){ 
    obj.wait();//û�����ˣ��ȴ� 
}

synchronize(obj){ 
    obj.notify();//�ж����ˣ����� 
}
����lock���������ˣ������ǣ�

lock.lock(); 
condition.await(); 
lock.unlock();	

lock.lock(); 
condition.signal(); 
lock.unlock();
Ϊ��ͻ������ʡ��������ϸ�ڡ����������㣺

1. lock������synchronize��ͬ�������װ������
2. ������Ҫ����һ������condition��
3. ͬ���ͻ��ѵĶ�����condition������lock����Ӧ�ķ�����await��signal��������wait��notify��
Ϊʲô��Ҫʹ��condition�أ���һ�仰��lock������ǰ�ķ�ʽֻ����һ���ȴ����У���ʵ��Ӧ��ʱ������Ҫ������������д��Ϊ���������ԣ�lock��ͬ��������ƺ͵ȴ����з��뿪�������Ᵽ֤��ĳ��ʱ��ֻ��һ���̷߳����ٽ�����lock�Լ���ɣ����ȴ����и��𱣴汻�������̣߳�condition��ɣ���

ͨ���鿴ReentrantLock��Դ���뷢�֣�condition��ʵ�ǵȴ����е�һ�������ߣ�conditionȷ�������Ķ���˳�򱻻��ѡ�

��Lock��ʵ���У�LockSupport������ʵ���߳�״̬�ĸı䣬����������һ���о�LockSupport��ʵ�ֻ��ơ�
*/