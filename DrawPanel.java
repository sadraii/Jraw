/*	Filename: DrawPanel.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


/**
*/
public class DrawPanel extends JPanel
{
	/**
		Constructs a new DrawPanel with double buffer and flow layout.
	*/
	public DrawPanel() {
		shapes = new ShapesList(this);
		tool = new SelectTool(this);
		
		setBorder(new LineBorder(Color.black));
		setBackground(Color.white);
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}

	/**
		If the UI delegate is non-null, calls its paint method. We pass the delegate a copy of the Graphics object to protect the rest of the paint code from irrevocable changes (for example, Graphics.translate()).
		@see java.awt.Graphics
	*/
	protected void paintComponent (Graphics g) {
		super.paintComponent(g);
		shapes.draw(g);
		shapes.drawHandles(g);
	}
	
	/**
		Returns the <code>ArrayList</code> of shapes in this <code>DrawPanel</code>.
		@return the <code>ArrayList</code> of shapes in this <code>DrawPanel</code>.
	*/
	public ShapesList getShapesList() {
		return shapes;
	}
	
	/**
		Returns the current <code>Tool</code>.
		@return the current <code>Tool</code>.
	*/
	public Tool getTool() {
		return tool;
	}
	
	/**
		Sets the current <code>Tool</code> to the specified <code>Tool</code>.
		@param tool the <code>Tool</code> to be made current
	*/
	public void installTool(Tool tool) {
		this.tool = tool;
		this.tool.prepare();
	}
	
	private ShapesList shapes;						//The list of shapes to be drawn on this panel.
	private Tool tool;								//The current tool being used.
	
	/*
		Forwards the actions to the current tool.
	*/
	private class MouseHandler extends MouseAdapter
	{
		public void mousePressed(MouseEvent e) { tool.mousePressed(e); }
		public void mouseClicked(MouseEvent e) { tool.mouseClicked(e); }
		public void mouseReleased(MouseEvent e) { tool.mouseReleased(e); }
	}
	
	/*
		Forwards the actions to the current tool.
	*/
	private class MouseMotionHandler implements MouseMotionListener
	{
		public void mouseMoved(MouseEvent e) { tool.mouseMoved(e); }
		public void mouseDragged(MouseEvent e) { tool.mouseDragged(e); }
	}
}