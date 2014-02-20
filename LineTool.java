/*	Filename: LineTool.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class LineTool extends Tool
{
	public LineTool(JPanel panel) {
		super(panel);
		g2 = null;
		lastPos = null;
		anchorPos = null;
	}
	
	public void mousePressed(MouseEvent e) {
		if(g2 == null) {
			g2 = (Graphics2D)panel.getGraphics();
			g2.setXORMode(Color.white);
		}
	
		anchorPos = e.getPoint();
		lastPos = e.getPoint();
	}
	
	public void mouseReleased(MouseEvent e) {
		if(g2 != null) {
			g2.dispose();
			g2 = null;
		}
		
		if(anchorPos != lastPos)
			shapes.add(
				new LineShape(
					(int)anchorPos.getX(),
					(int)anchorPos.getY(),
					(int)lastPos.getX(),
					(int)lastPos.getY(),
					Color.black
				)
			);
		
		panel.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		if(e.getPoint() != lastPos) {
			g2.drawLine(
				(int)anchorPos.getX(),
				(int)anchorPos.getY(),
				(int)lastPos.getX(),
				(int)lastPos.getY()
			);
			
			Point2D currentPos = e.getPoint();
	
			g2.drawLine(
				(int)anchorPos.getX(),
				(int)anchorPos.getY(),
				(int)currentPos.getX(),
				(int)currentPos.getY()
			);
			
			lastPos = currentPos;
		}
	}
	
	private Graphics2D g2;
	private Point2D lastPos;
	private Point2D anchorPos;
}
