/*	Filename: JrawFrame.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


/**
	A frame representing a document-based window in the Jraw application.
	@see javax.swing.JFrame
*/
public class JrawFrame extends JFrame
{
	/**
		Constructs a new frame representing a document-based window.
	*/
	public JrawFrame() {
		windowCount++;
		
		setTitle("JrawFrame" + windowCount);
		setLocation(windowCount * TILE_OFFSET, windowCount * TILE_OFFSET);
		setSize(WIDTH, HEIGHT);
		
		addWindowListener(
			new WindowAdapter () {
				public void windowClosing (WindowEvent e) {
					if(windowCount == 1)
						System.exit(0);
					else
						windowCount--;
				}
			}
		);
        
		JPanel drawPanel = new DrawPanel();
		JPanel buttonPanel = new ButtonPanel(drawPanel);
		getContentPane().add(drawPanel, "Center");
		getContentPane().add(buttonPanel, "North");

		createMenuBar(drawPanel);							// creates and populates the menu bar
		registry = createRegistry();						// creates the registry
	}
	
	public void createMenuBar(JPanel drawPanel) {
		//create the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//the File menu
		JMenu menu = new JMenu( "File" );
		menuBar.add(menu);
		
		//menu items for the File menu
		JMenuItem menuItem = new JMenuItem("New");
		menuItem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame j = new JrawFrame();
					j.show();
				}
			}
		);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open...");
		menuItem.addActionListener( new LoadListener(drawPanel) );
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Close");
		menuItem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(windowCount == 1)
						System.exit(0);
					else
						windowCount--;
					dispose();
				}
			}
		);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save...");
		menuItem.addActionListener( new SaveListener(drawPanel) );
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			}
		);
		
		menu.add(menuItem);

		//the Shapes menu
		menu = new JMenu("Shapes");
		menuBar.add(menu);
		
		//menu items for the Shapes menu
		menuItem = new JMenuItem("Group");
		menuItem.addActionListener(new GroupButtonListener(drawPanel));
		menu.add(menuItem);

		menuItem = new JMenuItem("Ungroup");
		menuItem.addActionListener(new UngroupButtonListener(drawPanel));
		menu.add(menuItem);

		menu.addSeparator();		
		
		//a submenu for Edge Color
		JMenu submenu = new JMenu("Edge Color");
		
		//items for the Edge Color submenu
		createEdgeColorSubmenuItems(submenu, drawPanel);
		menu.add(submenu);
		
		//a submenu for Fill Color
		submenu = new JMenu("Fill Color");
		
		//items for the Fill Color submenu
		createFillColorSubmenuItems(submenu, drawPanel);
		menu.add(submenu);
	}
	
	public void createEdgeColorSubmenuItems(JMenu submenu, JPanel drawPanel) {
		JMenuItem menuItem = new JMenuItem("No Color");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, null));
		submenu.add(menuItem);
		
		submenu.addSeparator();
		
		menuItem = new JMenuItem("Black");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.black));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Blue");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.blue));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Cyan");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.cyan));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Dark Gray");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.darkGray));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem( "Gray");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.gray));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Green");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.green));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Light Gray");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.lightGray));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Magenta");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.magenta));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Orange");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.orange));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Pink");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.pink));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Red");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.red));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("White");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.white));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Yellow");
		menuItem.addActionListener(new EdgeColorListener(drawPanel, Color.yellow));
		submenu.add(menuItem);
	}
	
	public void createFillColorSubmenuItems(JMenu submenu, JPanel drawPanel) {
		JMenuItem menuItem = new JMenuItem("No Color");
		menuItem.addActionListener(new FillColorListener(drawPanel, null));
		submenu.add(menuItem);
		
		submenu.addSeparator();
		
		menuItem = new JMenuItem("Black");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.black));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Blue");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.blue));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Cyan");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.cyan));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Dark Gray");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.darkGray));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem( "Gray");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.gray));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Green");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.green));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Light Gray");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.lightGray));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Magenta");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.magenta));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Orange");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.orange));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Pink");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.pink));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Red");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.red));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("White");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.white));
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Yellow");
		menuItem.addActionListener(new FillColorListener(drawPanel, Color.yellow));
		submenu.add(menuItem);
	}
	
	public ShapeRegistry createRegistry() {
		ShapeRegistry reg = ShapeRegistry.instance();
		
		reg.register(new LineShape());
		reg.register(new RectangleShape());
		reg.register(new EllipseShape());
		reg.register(new PolygonShape());
		reg.register(new GroupShape());
		
		return reg;
	}
	
	private static int windowCount = 0;						// number of windows that are open
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int TILE_OFFSET = 20;				// each new window is offset by this amount
	private ShapeRegistry registry;

	private class SaveListener implements ActionListener
	{
		public SaveListener(JPanel drawPanel) {
			panel = (DrawPanel)drawPanel;
		}
				
		public void actionPerformed(ActionEvent e) {
			String fileName = JOptionPane.showInputDialog("Save as:");
			if(fileName == null)
				return;
				
			ShapesList shapes = panel.getShapesList();
			
			try {
				PrintWriter out = new PrintWriter(new FileWriter(fileName));
				
				out.println(shapes.size());		//write the number of shapes in the shapes list
				for(int i = 0; i < shapes.size(); i++)
					((Shape)shapes.get(i)).writeToStream(out);
				
				if(out.checkError()) {
					JOptionPane.showMessageDialog( null, "An error occured while saving the file.", "Error", JOptionPane.ERROR_MESSAGE );
				}
				
				out.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		private DrawPanel panel;
	}
	
	private class LoadListener implements ActionListener
	{
		public LoadListener(JPanel drawPanel) {
			panel = (DrawPanel)drawPanel;
		}
		
		public void actionPerformed(ActionEvent e) {
			String fileName = JOptionPane.showInputDialog("Enter file name to open:");
			if(fileName == null)
				return;
			
			ShapesList shapes = panel.getShapesList();
			shapes.clear();							//clears the list
			
			try {
				BufferedReader in = new BufferedReader(new FileReader(fileName));
				int numShapes = Integer.parseInt(in.readLine());	// number of shapes in the file
				
				for(int i = 0; i < numShapes; i++) {
					shapes.add( i, registry.get(in.readLine()) );
					shapes.set( i, ((Shape)shapes.get(i)).readFromStream(in) );
				}
				
				in.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			
			panel.repaint();
		}
		
		private DrawPanel panel;
	}
	
	private class EdgeColorListener implements ActionListener
	{
		public EdgeColorListener(JPanel drawPanel, Color color) {
			panel = (DrawPanel)drawPanel;
			shapes = panel.getShapesList();
			this.color = color;
		}
		
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < shapes.selectionSize(); i++)
				shapes.selectionGet(i).setEdgeColor(color);
			
			for(int i = 0; i < shapes.size(); i++)
				if( shapes.selectionContainsElement(shapes.get(i)) )
					shapes.get(i).setEdgeColor(color);
					
			panel.repaint();
		}
				
		private DrawPanel panel;
		private ShapesList shapes;
		private Color color;
	}
	
	private class FillColorListener implements ActionListener
	{
		public FillColorListener(JPanel drawPanel, Color color) {
			panel = (DrawPanel)drawPanel;
			shapes = panel.getShapesList();
			this.color = color;
		}
		
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < shapes.selectionSize(); i++)
				shapes.selectionGet(i).setFillColor(color);
			
			for(int i = 0; i < shapes.size(); i++)
				if( shapes.selectionContainsElement(shapes.get(i)) )
					shapes.get(i).setFillColor(color);
			
			panel.repaint();
		}
		
		private DrawPanel panel;
		private ShapesList shapes;
		private Color color;
	}
	
	private class GroupButtonListener implements ActionListener
	{
		public GroupButtonListener(JPanel drawPanel) {
			panel = (DrawPanel)drawPanel;
			shapes = panel.getShapesList();
		}
		
		public void actionPerformed(ActionEvent e) {
			GroupShape gs = new GroupShape();
			int lowestX = panel.getSize().width;
			int lowestY = panel.getSize().height;
			int highestX = 0;
			int highestY = 0;
			
			for(int i = 0; i < shapes.selectionSize(); i++)					// remove from selection as you add to group
				gs.add(shapes.selectionRemove(i));
			
			for(int i = 0; i < gs.size(); i++) {
				if( (int)gs.get(i).getBounds2D().getMaxX() > highestX )
					highestX = (int)gs.get(i).getBounds2D().getMaxX();
				if( (int)gs.get(i).getBounds2D().getMaxY() > highestY )
					highestY = (int)gs.get(i).getBounds2D().getMaxY();
				if( (int)gs.get(i).getBounds2D().getMinX() < lowestX )
					lowestX = (int)gs.get(i).getBounds2D().getMinX();
				if( (int)gs.get(i).getBounds2D().getMinY() < lowestY )
					lowestY = (int)gs.get(i).getBounds2D().getMinY();
			}
			gs.setRect(lowestX, lowestY, highestX - lowestX, highestY - lowestY);
			
			shapes.add(gs);
			shapes.selectionAdd(gs);
			
			panel.repaint();
		}
		
		private DrawPanel panel;
		private ShapesList shapes;
	}
	
	private class UngroupButtonListener implements ActionListener
	{
		public UngroupButtonListener(JPanel drawPanel) {
			panel = (DrawPanel)drawPanel;
			shapes = panel.getShapesList();
		}
		
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < shapes.selectionSize(); i++)
				if(shapes.selectionGet(i) instanceof GroupShape) {
					for(int j = 0; j < ((GroupShape)shapes.selectionGet(i)).size(); j++)
						shapes.selectionAdd( ((GroupShape)shapes.selectionGet(i)).remove(j) );
					shapes.remove(shapes.selectionGet(i));	
					shapes.selectionRemove(shapes.selectionGet(i));
				}
				
			panel.repaint();
		}
		
		private DrawPanel panel;
		private ShapesList shapes;
	}
}