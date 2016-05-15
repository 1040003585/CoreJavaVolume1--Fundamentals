package _14_1什么是多线程.bounce;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: Bounce.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-15/下午07:51:54
 * Description: Shows an animated bouncing ball.
 */
public class Bounce {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new BounceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}

}
