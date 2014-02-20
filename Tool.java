/*	Filename: Tool.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;

abstract public class Tool implements MouseListener, MouseMotionListener
{	
	public Tool(JPanel drawPanel)
	{
		panel = (DrawPanel)drawPanel;
		this.shapes = panel.getShapesList();
	}
		
	public void prepare() { }						// no code
	
	public void mousePressed(MouseEvent e) { }		// no code
	public void mouseReleased(MouseEvent e) { }		// no code
	public void mouseClicked(MouseEvent e) { }		// no code
	public void mouseEntered(MouseEvent e) { }		// no code
	public void mouseExited(MouseEvent e) { }		// no code
	public void mouseMoved(MouseEvent e) { }		// no code
	public void mouseDragged( MouseEvent e ) { }	// no code
	
	protected DrawPanel panel;						//panel that this tool operates in
	protected ShapesList shapes;					//list of shapes to manipulate
}