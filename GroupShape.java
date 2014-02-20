/*	Filename: GroupShape.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;


public class GroupShape extends Shape implements Cloneable
{
	public GroupShape() {
		super();
		group = new Vector();
		underlyingShape = new Rectangle2D.Double();
	}
	
	public GroupShape(int x, int y, int w, int h) {
		super();
		group = new Vector();
		underlyingShape = new Rectangle2D.Double(x, y, w, h);
	}
	
	
	// ACCESSORS --------------------------------------------------------------
	
	public Color getEdgeColor() {
		return Color.black;
	}
	
	public Color getFillColor() {
		return null;
	}
	
	public int size() {
		return group.size();
	}
	
	public Shape get(int i) {
		return (Shape)group.get(i);
	}
	
	protected java.awt.Shape getUnderlyingShape() {
		return underlyingShape;
	}

	public int getX() {
		return (int)((Rectangle2D.Double)underlyingShape).getX();
	}

	public int getY() {
		return (int)((Rectangle2D.Double)underlyingShape).getY();
	}

	public int getWidth() {
		return (int)((Rectangle2D.Double)underlyingShape).width;
	}

	public int getHeight() {
		return (int)((Rectangle2D.Double)underlyingShape).height;
	}

	public void writeToStream(PrintWriter stream) {
		stream.println(
			getClass().getName() + "\n" +
			group.size() + "\n" +
			getX() + "\n" +
			getY() + "\n" +
			getWidth() + "\n" +
			getHeight()
		);
		for(int i = 0; i < group.size(); i++)
			((Shape)group.get(i)).writeToStream(stream);
	}

	protected void drawHandles(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		
		// draws the handles in clockwise order, starting in the upper left corner
		g2.drawRect(getX() - HANDLE_SIZE/2, getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
		g2.drawRect(getX() + getWidth() - HANDLE_SIZE/2, getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
		g2.drawRect(getX() + getWidth() - HANDLE_SIZE/2, getY() + getHeight() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
		g2.drawRect(getX() - HANDLE_SIZE/2, getY() + getHeight() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
	}
	
	public Object clone() {
		GroupShape cloned = (GroupShape)super.clone();
		cloned.group = (Vector)group.clone();							//clone internal data array
		for(int i = 0; i < group.size(); i++)							//clone each element in data array
			cloned.group.set( i, ((Shape)group.get(i)).clone() );
		
		return cloned;
	}
			
	public boolean contains(Point2D point) {
		return ((Rectangle2D.Double)underlyingShape).contains(point);
	}
	
	public Rectangle2D getBounds2D() {
		return ((Rectangle2D.Double)underlyingShape).getBounds2D();
	}
	
	public String toString() {
		System.out.println(getClass().getName() + " " + group.size());
		for(int i = 0; i < group.size(); i++)
			System.out.println(group.get(i).toString());
		return "";
	}
	
	// MUTATORS --------------------------------------------------------------
	
	/**
		Translates the location of every item in the <code>GroupShape</code> by the specified integer coordinates.
		@param deltaX the amount to translate along the <i>x</i> axis
		@param deltaY the amount to translate along the <i>y</i> axis
	*/
	public void translate(int deltaX, int deltaY) {
		for(int i = 0; i < group.size(); i++)
			((Shape)group.get(i)).translate(deltaX, deltaY);
		setRect(
			getX() + deltaX,
			getY() + deltaY,
			getWidth(),
			getHeight()
		);
	}
	
	public void setRect(int x, int y, int w, int h) {
		((Rectangle2D.Double)underlyingShape).setRect(x, y, w, h);
	}
	
	public Shape readFromStream(BufferedReader stream) {
		int numShapes;
		ShapeRegistry reg = ShapeRegistry.instance();
		GroupShape readGroup = new GroupShape();
		int lowestX = 2000;
		int lowestY = 2000;
		int highestX = 0;
		int highestY = 0;

		try {
			numShapes = Integer.parseInt(stream.readLine());
			readGroup.setRect(
				Integer.parseInt(stream.readLine()),
				Integer.parseInt(stream.readLine()),
				Integer.parseInt(stream.readLine()),
				Integer.parseInt(stream.readLine())
			);
			for(int i = 0; i < numShapes; i++)
				readGroup.add(reg.get(stream.readLine()).readFromStream(stream));
			
			
			return readGroup;
		}
		catch(IOException e) { e.printStackTrace(); }
	
		return null;
	}
	
	public void setEdgeColor(Color edge) {
		for(int i = 0; i < group.size(); i++)
			((Shape)group.get(i)).setEdgeColor(edge);
	}
	
	public void setFillColor(Color fill) {
		for(int i = 0; i < group.size(); i++)
			((Shape)group.get(i)).setFillColor(fill);
	}
	
	public void add(Shape shape) {
		group.add(shape);
	}
	
	public Shape remove(Shape shape) {
		return (Shape)group.remove(group.indexOf(shape));
	}
	
	public Shape remove(int i) {
		return (Shape)group.remove(i);
	}
	
	// FIELDS --------------------------------------------------------------
	
	private Vector group;
	private java.awt.Shape underlyingShape;
}
