/*	Filename: ButtonPanel.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
//import java.awt.geom.*;
import java.awt.event.*;


/**
*/
public class ButtonPanel extends JPanel
{
	public ButtonPanel (JPanel drawPanel) {
		this.panel = (DrawPanel)drawPanel;
		shapes = panel.getShapesList();
		setLayout( new FlowLayout(FlowLayout.LEFT) );				// layout of the buttons
		setBorder(new LineBorder(Color.black));
		
		// spacers used for the interface
		JButton spacer = new JButton();
		spacer.setEnabled(false);
		spacer.setBorderPainted(false);
		JButton spacer2 = new JButton();
		spacer2.setEnabled(false);
		spacer2.setBorderPainted(false);
		
		// create buttons
		selectButton = new JButton("Select");
		deleteButton = new JButton("Delete");
		lineButton = new JButton("Line");
		rectangleButton = new JButton("Rectangle");
		ellipseButton = new JButton( "Ellipse" );
		polygonButton = new JButton( "Polygon" );
		edgeColorButton = new JButton( "Edge Color" );
		fillColorButton = new JButton( "Fill Color" );
		
		// make them not focusable
		selectButton.setFocusPainted(false);
		deleteButton.setFocusPainted(false);
		lineButton.setFocusPainted(false);
		rectangleButton.setFocusPainted(false);
		ellipseButton.setFocusPainted(false);
		polygonButton.setFocusPainted(false);
		edgeColorButton.setFocusPainted(false);
		fillColorButton.setFocusPainted(false);
		
		// add them to the panel
		add(selectButton);
		add(deleteButton);
		add(spacer);
		add(lineButton);
		add(rectangleButton);
		add(ellipseButton);
		add(polygonButton);
		add(spacer2);
		add(edgeColorButton);
		add(fillColorButton);
		
		// the action listeners for the buttons
		selectButton.addActionListener( new ShapeToolButtonListener(new SelectTool(panel)) );
		deleteButton.addActionListener( new ShapeToolButtonListener(new DeleteTool(panel)) );
		lineButton.addActionListener( new ShapeToolButtonListener(new LineTool(panel)) );
		rectangleButton.addActionListener( new ShapeToolButtonListener(new RectangleTool(panel)) );
		ellipseButton.addActionListener( new ShapeToolButtonListener(new EllipseTool(panel)) );
		polygonButton.addActionListener( new ShapeToolButtonListener(new PolygonTool(panel)) );
		edgeColorButton.addActionListener( new EdgeColorButtonListener(panel) );
		fillColorButton.addActionListener( new FillColorButtonListener(panel) );
		
		// buttons are grouped, the selected button is highlighted
		ButtonGroup group = new ButtonGroup();
		group.add(selectButton);
		group.add(deleteButton);
		group.add(lineButton);
		group.add(rectangleButton);
		group.add(ellipseButton);
		group.add(polygonButton);
		group.add(edgeColorButton);
		group.add(fillColorButton);
		
		//sets the SelectTool as the default button when a window opens
		selectButton.setBackground(Color.gray);		
	}
	
	private DrawPanel panel;
	private ShapesList shapes;						// NEEDED???

	private JButton selectButton;
	private JButton deleteButton;
	
	private JButton lineButton;
	private JButton rectangleButton;
	private JButton ellipseButton;
	private JButton polygonButton;
	
	private JButton edgeColorButton;
	private JButton fillColorButton;
	
	/**
		The <code>ButtonGroup</code> class is used to create a group of <code>JButton</code>s. It also sets the background of the selected <code>JButton</code> to gray.
		@see java.awt.Color
	*/
	private class ButtonGroup implements ActionListener
	{
		/**
			Constructs an empty <code>ButtonGroup</code>.
		*/
		public ButtonGroup() {
			buttons = new ArrayList();
		}
		
		/**
			Adds a button to the group.
			@param button the <code>JButton</code> being added
		*/
		public void add(JButton button) {
			buttons.add(button);
			button.addActionListener(this);					// register self as listener for the button
		}
		
		/**
			Sets the background color of the selected button to gray.
			@param e the event generated by the <code>JButton</code> being pressed
			@see java.awt.Color
		*/
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < buttons.size(); i++) {
				if(buttons.get(i) == e.getSource())
					((JButton)buttons.get(i)).setBackground(Color.gray);
				else
					((JButton)buttons.get(i)).setBackground(getBackground());
			}
		}
		
		private ArrayList buttons;
	}
	
	private class ShapeToolButtonListener implements ActionListener
	{
		public ShapeToolButtonListener(Tool tool) { this.tool = tool; }
		public void actionPerformed(ActionEvent e) { panel.installTool(tool); }
		
		private Tool tool;
	}
}