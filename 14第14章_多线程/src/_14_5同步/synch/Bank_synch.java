package _14_5同步.synch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: Bank_synch.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-16/下午10:47:15
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
 
Lock的await/singal 和 Object的wait/notify 的区别

在使用Lock之前，我们都使用Object 的wait和notify实现同步的。举例来说，一个producer和consumer，consumer发现没有东西了，等待，produer生成东西了，唤醒。

线程consumer	 线程producer
synchronize(obj){ 
    obj.wait();//没东西了，等待 
}

synchronize(obj){ 
    obj.notify();//有东西了，唤醒 
}
有了lock后，世道变了，现在是：

lock.lock(); 
condition.await(); 
lock.unlock();	

lock.lock(); 
condition.signal(); 
lock.unlock();
为了突出区别，省略了若干细节。区别有三点：

1. lock不再用synchronize把同步代码包装起来；
2. 阻塞需要另外一个对象condition；
3. 同步和唤醒的对象是condition而不是lock，对应的方法是await和signal，而不是wait和notify。
为什么需要使用condition呢？简单一句话，lock更灵活。以前的方式只能有一个等待队列，在实际应用时可能需要多个，比如读和写。为了这个灵活性，lock将同步互斥控制和等待队列分离开来，互斥保证在某个时刻只有一个线程访问临界区（lock自己完成），等待队列负责保存被阻塞的线程（condition完成）。

通过查看ReentrantLock的源代码发现，condition其实是等待队列的一个管理者，condition确保阻塞的对象按顺序被唤醒。

在Lock的实现中，LockSupport被用来实现线程状态的改变，后续将更进一步研究LockSupport的实现机制。
*/