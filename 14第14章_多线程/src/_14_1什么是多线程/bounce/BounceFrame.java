package _14_1什么是多线程.bounce;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: BounceFrame.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-15/下午07:59:08
 * Description: The frame with ball component and buttons.
 */
public class BounceFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BallComponent comp;
	public static final int STEPS=2000;
	public static final int DELAY=1;//ms
	
	/**
	 * The frame with ball component and buttons.
	 */
	public BounceFrame(){
		setTitle("Bounce");
		
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
				try {
					Ball ball = new Ball();
					comp.add(ball);
					for (int i = 0; i <STEPS; i++) {
						ball.move(comp.getBounds());
						comp.paint(comp.getGraphics());
//						comp.repaint();
						Thread.sleep(DELAY);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
		
		/**
		 * Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. 
		 * The resulting width and height of the window are automatically enlarged 
		 * if either of dimensions is less than the minimum size as specified by the previous call to the setMinimumSize method. 

		 * If the window and/or its owner are not displayable yet, 
		 * both of them are made displayable before calculating the preferred size. 
		 * The Window is validated after its size is being calculated.
		 */
		pack();//显示
	}

	/** Adds a button to a container.
	 * @param c the container
	 * @param title the button title
	 * @param actionListener the action listener for the button
	 */
	private void addButton(Container c, String title,
			ActionListener listener) {
		// TODO Auto-generated method stub
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
}
