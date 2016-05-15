package _14_1什么是多线程.bounceThread;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import _14_1什么是多线程.bounce.Ball;
import _14_1什么是多线程.bounce.BallComponent;

/**
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: BounceFrame.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-15/下午10:38:27
 * Description: The BounceFrame with panel and buttons.
 */

public class BounceFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private BallComponent comp;
	
	/**
	 * Constructs the frame with the component for showing the bouncing ball and Start and Close buttons.
	 */
	public BounceFrame(){
		
		comp=new BallComponent();
		add(comp,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel,"Start",new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addBall();
			}

			private void addBall() {
				// TODO Auto-generated method stub
				Ball b = new Ball();
				comp.add(b);
				Runnable r=new BallRunnable(b,comp);
				Thread t = new Thread(r);
				t.start();
			}
			
		});
		addButton(buttonPanel, "Close", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});

		add(buttonPanel,BorderLayout.SOUTH);
		pack();
	}

	/**Adds a button to a container.
	 * @param c the container
	 * @param title the button title
	 * @param listener the action listener for the button
	 */
	private void addButton(Container c, String title,
			ActionListener listener) {
		// TODO Auto-generated method stub
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
}
