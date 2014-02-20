/*	Filename: FillColorButtonListener.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


//import javax.swing.*;
//import javax.swing.border.*;
//import java.awt.*;
//import java.util.*;
//import java.awt.geom.*;
import java.awt.event.*;


public class FillColorButtonListener extends AbstractColorButtonListener
{
	public FillColorButtonListener(DrawPanel drawPanel) {
		super(drawPanel);
	}

	public void actionPerformed(ActionEvent e) {
		createColorDialog("Fill Color");
	}
	
	public void okButtonActionPerformed(ActionEvent e) {
		ShapesList shapes = panel.getShapesList();
		
		for(int i = 0; i < shapes.selectionSize(); i++)
			shapes.selectionGet(i).setFillColor(selectedColor);
		
		for(int i = 0; i < shapes.size(); i++)
			if( shapes.selectionContainsElement(shapes.get(i)) )
				shapes.get(i).setFillColor(selectedColor);
	}
}
