package _14_1什么是多线程.bounce;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: BallComponent.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-5-15/下午08:12:06
 * Description: The component that draws the balls.
 */
public class BallComponent extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH=450;
	private static final int DEFAULT_HEIGHT=350;
	
	private java.util.List<Ball>balls=new ArrayList<Ball>();
	
	/**
	 * Add a ball to the component
	 * @param the ball to add
	 */
	public void add(Ball ball){
		balls.add(ball);
	}
	

	/*
	 *  comp.paint();(non-Javadoc)
	*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);//erase background
		Graphics2D g2=(Graphics2D)g;
		for(Ball ball:balls){
			g2.fill(ball.getShape());
		}
	}
	
	
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
}
