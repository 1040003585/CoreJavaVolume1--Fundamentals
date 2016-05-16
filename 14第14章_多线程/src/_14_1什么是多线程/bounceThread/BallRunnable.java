package _14_1什么是多线程.bounceThread;

import java.awt.Component;

import _14_1什么是多线程.bounce.Ball;

/**
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-15/下午10:30:27
 * Description: A runnable that animates a bouncing ball.
 */
public class BallRunnable implements Runnable {

	private Ball ball;
	private Component component;
	public static final int STEPS=2000;
	public static final int DELEY=1;//ms

	/**
	 * Constructs the runnable.
	 * @param aBall the ball to bounce
	 * @param aComponent the component in which the ball bounces
	 */
	public BallRunnable(Ball aBall,Component aComponent){
		ball=aBall;
		component=aComponent;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(int i=0;i<STEPS;i++){
				ball.move(component.getBounds());
//				component.paint(component.getGraphics());
				component.repaint();
				Thread.sleep(DELEY);
			}
		
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}
