/*	Filename: AbstractColorButtonListener.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
//import java.awt.geom.*;
import java.awt.event.*;


abstract public class AbstractColorButtonListener implements ActionListener
{
	public AbstractColorButtonListener(DrawPanel drawPanel) {
		colorDialog = null;
		panel = drawPanel;
		selectedColor = null;
		colorButtonList = new ArrayList();
	}
	
	abstract public void actionPerformed(ActionEvent e);
	
	public void createColorDialog(String title) {
		colorDialog = new JDialog();
		colorDialog.setTitle(title);
		colorDialog.setModal(true);
		colorDialog.setResizable(false);
		colorDialog.setSize(199, 161);
	
		JPanel colorButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		createSpacerButton(colorButtons);
		createSpacerButton(colorButtons);
		
		JButton noColor = new JButton("N");							// "N" is the default button
		noColor.setFocusPainted(false);
		noColor.setBorderPainted(true);
		noColor.addActionListener(new ColorButtonListener(null));
		colorButtonList.add(noColor);
		colorButtons.add(noColor);
		
		createSpacerButton(colorButtons);
		createSpacerButton(colorButtons);
	
		createColorButton(Color.black, colorButtons);
		createColorButton(Color.blue, colorButtons);
		createColorButton(Color.cyan, colorButtons);
		createColorButton(Color.darkGray, colorButtons);
		createColorButton(Color.gray, colorButtons);
		createColorButton(Color.green, colorButtons);
		createColorButton(Color.lightGray, colorButtons);
		createColorButton(Color.magenta, colorButtons);
		createColorButton(Color.orange, colorButtons);
		createColorButton(Color.pink, colorButtons);
		
		createSpacerButton(colorButtons);

		createColorButton(Color.red, colorButtons);
		createColorButton(Color.white, colorButtons);
		createColorButton(Color.yellow, colorButtons);
	
		createSpacerButton(colorButtons);
		
		colorDialog.getContentPane().add(colorButtons, "Center");
		
		JButton cancel = new JButton("Cancel");
		cancel.setFocusPainted(false);
		cancel.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					colorDialog.dispose();
					colorDialog = null;
				}
			}
		);
		
		JButton ok = new JButton("OK");
		ok.setFocusPainted(false);
		ok.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					okButtonActionPerformed(e);
					
					panel.repaint();
					colorDialog.dispose();
					colorDialog = null;
				}
			}
		);
		
//		ok.setBorder(BorderFactory.createLineBorder(Color.yellow));
//		ok.setBorderPainted(false);
		
		JPanel commands = new JPanel();
		commands.add(cancel);
		commands.add(ok);
		commands.setBorder(new LineBorder(Color.black, 2, true));
		
		colorDialog.getContentPane().add(commands, "South");
		
		colorDialog.show();
	}
	
	abstract public void okButtonActionPerformed(ActionEvent e);
	
	public void createColorButton(Color color, JPanel panel) {
		JButton button = new JButton(" ");
		button.setBackground(color);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.addActionListener(new ColorButtonListener(color));
		colorButtonList.add(button);
		panel.add(button);
	}
	
	public void createSpacerButton(JPanel panel) {
		JButton spacerButton = new JButton(" ");
		spacerButton.setFocusPainted(false);
		spacerButton.setBorderPainted(false);
		spacerButton.setEnabled(false);
		panel.add(spacerButton);
	}
	
	
	protected DrawPanel panel;
	protected JDialog colorDialog;
	protected Color selectedColor;
	protected ArrayList colorButtonList;
	
	protected class ColorButtonListener implements ActionListener
	{
		public ColorButtonListener(Color color) {
			this.color = color;
		}
		
		public void actionPerformed(ActionEvent e) {
			selectedColor = color;
			for(int i = 0; i < colorButtonList.size(); i++)
				((JButton)colorButtonList.get(i)).setBorderPainted(false);
			((JButton)e.getSource()).setBorderPainted(true);
		}
		
		private Color color;
	}
}
