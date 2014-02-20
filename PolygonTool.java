/*	Filename: PolygonTool.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class PolygonTool extends Tool
{
	public PolygonTool(JPanel panel) {
		super(panel);
		g2 = null;
		xPoints = null;
		yPoints = null;
		nPoints = 0;
		firstDrag = true;
	}
	
	public void mousePressed(MouseEvent e) {
		if(g2 == null) {
			g2 = (Graphics2D)panel.getGraphics();
			g2.setXORMode(Color.white);
		}

		if(nPoints == 0) {
			xPoints = new int[] {e.getX()};
			yPoints = new int[] {e.getY()};
			nPoints = 1;
		}
		else {
			g2.drawPolyline(xPoints, yPoints, nPoints);
			
			/*if(DRAW) { ///////////////
				System.out.print("draw +[");
				DRAW = false;
			}
			else {
				System.out.print("undr -[");
				DRAW = true;
			}
			for(int i = 0; i < nPoints; i++)
				System.out.print("(" + xPoints[i] + " " + yPoints[i] + ") ");
			System.out.println("] pressed:before add");*/
			
			addPoints(e.getX(), e.getY());
			g2.drawPolyline(xPoints, yPoints, nPoints);
			
			/*if(DRAW) { ///////////////
				System.out.print("draw +[");
				DRAW = false;
			}
			else {
				System.out.print("undr -[");
				DRAW = true;
			}
			for(int i = 0; i < nPoints; i++)
				System.out.print("(" + xPoints[i] + " " + yPoints[i] + ") ");
			System.out.println("] pressed:after add");*/
		}
		
		/*if(DRAW) { //////////////
			System.out.print("draw +[");
			DRAW = false;
		}
		else {
			System.out.print("undr -[");
			DRAW = true;
		}
		for(int i = 0; i < nPoints; i++)
			System.out.print("(" + xPoints[i] + " " + yPoints[i] + ") ");
		System.out.println("] pressed:general"); */
	}
	
	public void mouseReleased(MouseEvent e) {
		firstDrag = true;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() > 1) {
			int[] temp = new int[nPoints - 1];
			System.arraycopy(xPoints, 0, temp, 0, nPoints - 1);
			xPoints = temp;
			
			temp = new int[nPoints - 1];
			System.arraycopy(yPoints, 0, temp, 0, nPoints - 1);
			yPoints = temp;
			
			nPoints--;
			
			shapes.add(
				new PolygonShape(
					xPoints,
					yPoints,
					nPoints,
					Color.black,
					null
				)
			);
			
			if(g2 != null) {
				g2.dispose();
				g2 = null;
			}
			xPoints = null;
			yPoints = null;
			nPoints = 0;
			
			panel.repaint();
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(nPoints != 0) {
			g2.drawPolyline(xPoints, yPoints, nPoints);
			
			/*if(DRAW) { //////////
				System.out.print("draw +[");
				DRAW = false;
			}
			else {
				System.out.print("undr -[");
				DRAW = true;
			}
			for(int i = 0; i < nPoints; i++)
				System.out.print("(" + xPoints[i] + " " + yPoints[i] + ") ");
			System.out.println("] moved:first");*/
			
			if(firstDrag) {
				addPoints(e.getX(), e.getY());
				firstDrag = false;
			}
			else {
				xPoints[nPoints - 1] = e.getX();
				yPoints[nPoints - 1] = e.getY();
			}
			
			g2.drawPolyline(xPoints, yPoints, nPoints);
			
			/*if(DRAW) {	////////////
				System.out.print("draw +[");
				DRAW = false;
			}
			else {
				System.out.print("undr -[");
				DRAW = true;
			}
			for(int i = 0; i < nPoints; i++)
				System.out.print("(" + xPoints[i] + " " + yPoints[i] + ") ");
			System.out.println("] moved:second");*/
		}
	}
	
	public void addPoints(int x, int y) {
		int[] temp = new int[nPoints + 1];
		System.arraycopy(xPoints, 0, temp, 0, nPoints);
		temp[nPoints] = x;
		xPoints = temp;
		
		temp = new int[nPoints + 1];
		System.arraycopy(yPoints, 0, temp, 0, nPoints);
		temp[nPoints] = y;
		yPoints = temp;
		
		nPoints++;
	}
	
//	private boolean DRAW = true;
	
	private Graphics2D g2;
	private int[] xPoints;
	private int[] yPoints;
	private int nPoints;
	private boolean firstDrag;
}
