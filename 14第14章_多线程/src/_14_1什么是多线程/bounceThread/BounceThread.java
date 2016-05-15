package _14_1什么是多线程.bounceThread;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: BounceThread.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-15/下午10:25:59
 * Description: Shows animated bouncing balls.
 */
public class BounceThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				
				JFrame frame = new BounceFrame();
				frame.setTitle("BounceThread");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}

}
