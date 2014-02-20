/*	Filename: SelectTool.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DeleteTool extends Tool
{
	public DeleteTool(JPanel panel) {
		super(panel);
	}
	
	public void mousePressed(MouseEvent e) {
		if( shapes.contains(e.getPoint()) ) {
			for(int i = 0; i < shapes.selectionSize(); i++)
				if(shapes.shapeThatContains(e.getPoint()) == shapes.selectionGet(i))
					shapes.selectionRemove(i);
			shapes.remove( shapes.shapeThatContains(e.getPoint()) );
			
			panel.repaint();
		}
	}
	
	// set the mouse cursor to cross hairs if it is on a shape
	public void mouseMoved(MouseEvent e)
	{
		if ( shapes.contains(e.getPoint()) )
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		else
			panel.setCursor(Cursor.getDefaultCursor());
	}			
}
