/*	Filename: EllipseTool.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class EllipseTool extends Tool
{
	public EllipseTool(JPanel panel) {
		super(panel);
		g2 = null;
		lastPos = null;
		anchorPos = null;
		rw = null;
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
		
		if(rw != null)
			shapes.add(
				new EllipseShape(
					rw.x,
					rw.y,
					rw.w,
					rw.h,
					Color.black,
					null
				)
			);
		
		panel.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		if(e.getPoint() != lastPos) {
			rw = calculateRectCoords(anchorPos, lastPos);
			g2.drawOval(rw.x, rw.y, rw.w, rw.h);
			
			Point2D currentPos = e.getPoint();
			
			rw = calculateRectCoords(anchorPos, currentPos);
			g2.drawOval(rw.x, rw.y, rw.w, rw.h);
			
			lastPos = currentPos;
		}
	}

	private RectWrapper calculateRectCoords(Point2D anchorPos, Point2D nextPos) {
		if( (nextPos.getX() < anchorPos.getX()) && (nextPos.getY() < anchorPos.getY()) )
			return new RectWrapper(
				(int)nextPos.getX(),
				(int)nextPos.getY(),
				(int)(anchorPos.getX()-nextPos.getX()),
				(int)(anchorPos.getY()-nextPos.getY())
			);
		else if( nextPos.getY() < anchorPos.getY() )
			return new RectWrapper(
				(int)anchorPos.getX(),
				(int)nextPos.getY(),
				(int)(nextPos.getX()-anchorPos.getX()),
				(int)(anchorPos.getY()-nextPos.getY())
			);
		else if( nextPos.getX() < anchorPos.getX() )
			return new RectWrapper(
				(int)nextPos.getX(),
				(int)anchorPos.getY(),
				(int)(anchorPos.getX()-nextPos.getX()),
				(int)(nextPos.getY()-anchorPos.getY())
			);
		else
			return new RectWrapper(
				(int)anchorPos.getX(),
				(int)anchorPos.getY(),
				(int)(nextPos.getX()-anchorPos.getX()),
				(int)(nextPos.getY()-anchorPos.getY())
			);
	}

	private Graphics2D g2;
	private Point2D lastPos;
	private Point2D anchorPos;
	private RectWrapper rw;
	
	/*
		Small class that wraps the values that a typical rectangle would use.
	*/
	private class RectWrapper
	{
		public RectWrapper(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
		
		public int x, y, w, h;
	}
}
