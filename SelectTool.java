/*	Filename: SelectTool.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class SelectTool extends Tool
{
	public SelectTool(JPanel panel) {
		super(panel);
		g2 = null;
		lastPos = null;
		anchorPos = null;
		rw = null;
		selectionRectangle = null;
		
		float[] dash = {DASH_LINE_LENGTH, DASH_GAP_LENGTH};
		solidStroke = null;
		dashedStroke = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
	}
	
	public void prepare() {
		shapes.selectionClear();
	}
	
	public void mousePressed(MouseEvent e) {
		if(g2 == null) {
			g2 = (Graphics2D)panel.getGraphics();
			g2.setXORMode(Color.white);
			solidStroke = (BasicStroke)g2.getStroke();
		}
	
		anchorPos = e.getPoint();
		lastPos = e.getPoint();
		
		if(e.isShiftDown()) {
			if( shapes.contains(e.getPoint()) ) {
				shapes.selectionAdd( shapes.shapeThatContains(e.getPoint()) );
				repositionShapes = true;									// SYNC WITH DRAG
				drawSelectionRectangle = false;
				drawInverseSelectionRectangle = false;
			}
			else {
				drawInverseSelectionRectangle = true;
				drawSelectionRectangle = false;
				repositionShapes = false;
			}
		}
		else {
			shapes.selectionClear();
			
			if( shapes.contains(e.getPoint()) ) {
				shapes.selectionAdd( shapes.shapeThatContains(e.getPoint()) );
				repositionShapes = true;									// SYNC WITH DRAG
				drawSelectionRectangle = false;
				drawInverseSelectionRectangle = false;
			}
			else {
				drawSelectionRectangle = true;
				drawInverseSelectionRectangle = false;
				repositionShapes = false;
			}
		}
		
		// sets the stroke as solid or dashed based on the current mouse operation
		if(repositionShapes == true)
			g2.setStroke(solidStroke);
		else
			g2.setStroke(dashedStroke);

	}
	
	public void mouseReleased(MouseEvent e) {
		if(g2 != null) {
			g2.dispose();
			g2 = null;
		}
		
//		System.out.println("# " + shapes.selectionSize());
		panel.repaint();
	}

	// set the mouse cursor to cross hairs if it is on a shape
	public void mouseMoved(MouseEvent e)
	{
		if (shapes.contains(e.getPoint()))
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		else
			panel.setCursor(Cursor.getDefaultCursor());
	}
	
	public void mouseDragged(MouseEvent e) {
		if(repositionShapes) {
			shapes.selectionDraw((Graphics)g2);
			shapes.drawHandles((Graphics)g2);
			
			int deltaX = e.getX() - (int)lastPos.getX();
			int deltaY = e.getY() - (int)lastPos.getY();
			shapes.selectionRepositionBy(deltaX, deltaY);
			
			shapes.selectionDraw((Graphics)g2);
			shapes.drawHandles((Graphics)g2);
			
			lastPos = e.getPoint();
		}
		else if((e.getPoint() != lastPos) && (drawSelectionRectangle || drawInverseSelectionRectangle)) {
			rw = calculateRectCoords(anchorPos, lastPos);
			g2.drawRect(rw.x, rw.y, rw.w, rw.h);
			
			Point2D currentPos = e.getPoint();
			
			rw = calculateRectCoords(anchorPos, currentPos);
			g2.drawRect(rw.x, rw.y, rw.w, rw.h);
			
			// prepare the selection rectangle with the current mouse position values
			if(selectionRectangle == null)
				selectionRectangle = new Rectangle2D.Double(rw.x, rw.y, rw.w, rw.h);
			else
				selectionRectangle.setRect(rw.x, rw.y, rw.w, rw.h);
				
			for(int i = 0; i < shapes.size(); i++)
				if( selectionRectangle.contains(shapes.get(i).getBounds2D()) ) {
					if(drawSelectionRectangle) {
							shapes.selectionAdd(shapes.get(i));
							//System.out.println("selectionadd=" + shapes.selectionSize());
					}
					else {														//drawInverseSelectionRectangle
						for(int j = 0; j < shapes.selectionSize(); j++) {
							if(shapes.selectionGet(j) == shapes.get(i))
								shapes.selectionRemove(shapes.selectionGet(j));
							else
								shapes.selectionAdd(shapes.get(i));
						}
					}
				}
			
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
	private BasicStroke solidStroke;
	private BasicStroke dashedStroke;
	private Point2D lastPos;
	private Point2D anchorPos;
	private RectWrapper rw;
	private final float DASH_LINE_LENGTH = 5.0f;
	private final float DASH_GAP_LENGTH = 5.0f;
	private Rectangle2D.Double selectionRectangle;
	private boolean repositionShapes;
	private boolean drawSelectionRectangle;
	private boolean drawInverseSelectionRectangle;
	
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
