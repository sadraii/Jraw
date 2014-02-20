/*	Filename: LineShape.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import java.io.*;
import java.awt.*;
import java.awt.geom.*;


/**
	The <code>LineShape</code> class represents a line in 2D space specified with integer coordinates. It also contains an edge color specified with a <code>Color</code> object.
	@see java.awt.geom.Line2D.Double
	@see java.awt.Color
*/
public class LineShape extends EdgedShape implements Cloneable
{
	/**
		Constructs and initializes a <code>LineShape</code> with coordinates (0, 0) -> (0, 0).
	*/
	public LineShape() {
		super();
		underlyingShape = new Line2D.Double();
	}
	
	/**
		Constructs and initializes a <code>LineShape</code> with the specified coordinates.
		@param x1,&nbsp;y1 the first specified coordinate
		@param x2,&nbsp;y2 the second secified coordinate
	*/
	public LineShape(int x1, int y1, int x2, int y2) {
		super();
		underlyingShape = new Line2D.Double(x1, y1, x2, y2);
	}
	
	/**
		Constructs and initializes a <code>LineShape</code> with the specified <code>Point2D</code> objects.
		@param p1,&nbsp;p2 the specified <code>Point2D</code> objects
	*/
	public LineShape(Point2D p1, Point2D p2) {
		super();
		underlyingShape = new Line2D.Double(p1, p2);
	}	
	
	/**
		Constructs and initializes a <code>LineShape</code> with the specified coordinates and edge color.
		@param x1,&nbsp;y1 the first specified coordinates
		@param x2,&nbsp;y2 the second specified coordinates
		@param edge the edge color
	*/
	public LineShape(int x1, int y1, int x2, int y2, Color edge) {
		super(edge);
		underlyingShape = new Line2D.Double(x1, y1, x2, y2);
	}
	
	/**
		Constructs and initializes a <code>LineShape</code> with the specified <code>Point2D</code> objects and edge color.
		@param p1,&nbsp;p2 the specified <code>Point2D</code>objects
		@param edge the edge color
	*/
	public LineShape(Point2D p1, Point2D p2, Color edge) {
		super(edge);
		underlyingShape = new Line2D.Double(p1, p2);
	}
	
	// ACCESSORS --------------------------------------------------------------
	
	/**
		Returns the underlying <code>Line2D.Double</code> shape.
		@return the underlying <code>Line2D.Double</code> shape.
		@see java.awt.geom.Line2D.Double
	*/
	protected java.awt.Shape getUnderlyingShape() {
		return underlyingShape;
	}
	
	/**
		Returns the X coordinate of the start point in integer precision.
		@return the X coordinate of this <code>LineShape</code> object's starting point.
	*/
	public int getX1() {
		return (int)((Line2D.Double)underlyingShape).x1;
	}
	
	/**
		Returns the Y coordinate of the start point in integer precision.
		@return the Y coordinate of this <code>LineShape</code> object's starting point.
	*/
	public int getY1() {
		return (int)((Line2D.Double)underlyingShape).y1;
	}
	
	/**
		Returns the starting <code>Point2D</code> of this <code>LineShape</code>.
		@return the starting <code>Point2D</code> of this <code>LineShape</code>.
	*/
	public Point2D getP1() {
		return ((Line2D.Double)underlyingShape).getP1();
	}
	
	/**
		Returns the X coordinate of the end point in integer precision.
		@return the X coordinate of this <code>LineShape</code> object's ending point.
	*/
	public int getX2() {
		return (int)((Line2D.Double)underlyingShape).x2;
	}
	
	/**
		Returns the X coordinate of the end point in integer precision.
		@return the X coordinate of this <code>LineShape</code> object's ending point.
	*/
	public int getY2() {
		return (int)((Line2D.Double)underlyingShape).y2;
	}
	
	/**
		Returns the ending <code>Point2D</code> of this <code>LineShape</code>.
		@return the ending <code>Point2D</code> of this <code>LineShape</code>.
	*/
	public Point2D getP2() {
		return ((Line2D.Double)underlyingShape).getP2();
	}
	
	/**
		Writes this object's state to a <code>PrintWriter</code> stream.
		The state is written in the following manner (without any of the comments or the "----"):
		----
		LineShape				// the class name
		<int>						// the first x coordinate
		<int>						// the first y coordinate
		<int>						// the second x coordinate
		<int>						// the second y coordinate
		<int>						// the red component of the edge color
		<int>						// the green component of the edge color*
		<int>						// the blue component of the edge color*
		----
		* if the edge or fill color is null, the red component is replaced with "-1" and the green and blue components are not written out.
		@param stream the <code>PrintWriter</code> stream to write the state to
	*/
	public void writeToStream(PrintWriter stream) {
		stream.println(												// write location
			getClass().getName() + "\n" +
			(int)((Line2D.Double)underlyingShape).x1 + "\n" +
			(int)((Line2D.Double)underlyingShape).y1 + "\n" +
			(int)((Line2D.Double)underlyingShape).x2 + "\n" +
			(int)((Line2D.Double)underlyingShape).y2
		);
		
		if(getEdgeColor() == null)									// write edge color
			stream.println("-1");
		else
			stream.println(
				getEdgeColor().getRed() + "\n" +
				getEdgeColor().getGreen() + "\n" +
				getEdgeColor().getBlue()
			);
	}
	
	/**
		Draws the handles of the <code>LineShape</code> using a <code>Graphics</code> object.
		@param g the <code>Graphics</code> object
	*/
	protected void drawHandles(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		
		g2.drawRect(getX1() - HANDLE_SIZE/2, getY1() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
		g2.drawRect(getX2() - HANDLE_SIZE/2, getY2() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
	}
	
	/**
		Creates a new object of the same class with the same contents as this object.
		@return a clone of this instance.
		@throws OutOfMemoryError if there is not enought memory.
		@see java.lang.Cloneable
	*/
	public Object clone() {
		LineShape cloned = (LineShape)super.clone();
//		cloned.underlyingShape = (java.awt.Shape)underlyingShape.clone();

		return cloned;
	}
	
	/**
		Returns true if the shape contains the <code>Point2D</code> object; false otherwise.
		@param point the <code>Point2D</code> object
		@return true if the shape contains the point; false otherwise.
	*/
	public boolean contains(Point2D point) {
		if( ((Line2D.Double)underlyingShape).ptSegDist(point) < DIST_FROM_LINE )
			return true;
		
		return false;
	}
	
	/**
		Returns a high precision bounding box of the <code>LineShape</code> object.
		@return a high precision bounding box of the <code>LineShape</code> object.
	*/
	public Rectangle2D getBounds2D() {
		return ((Line2D.Double)underlyingShape).getBounds2D();
	}
	
	/**
		Returns a <code>String</code> representation of the object. 
		@return a <code>String</code> representation of the object.
	*/
	public String toString() {
		return getClass().getName() + "["
		+ "P1=" + "(" + "x=" + ((Line2D.Double)underlyingShape).x1 + ", "
		+ "y=" + ((Line2D.Double)underlyingShape).y1 + ")" + ", "
		+ "P2=" + "(" + "x=" + ((Line2D.Double)underlyingShape).x2 + ", "
		+ "y=" + ((Line2D.Double)underlyingShape).y2 + ")" + ", "
		+ "edgeColor=" + getEdgeColor() + "]";
	}

	// MUTATORS --------------------------------------------------------------
	
	/**
		Translates the location of the <code>LineShape</code> by the specified integer coordinates.
		@param deltaX the amount to translate along the <i>x</i> axis
		@param deltaY the amount to translate along the <i>y</i> axis
	*/
	public void translate(int deltaX, int deltaY) {
		setLine(
			getX1() + deltaX,
			getY1() + deltaY,
			getX2() + deltaX,
			getY2() + deltaY
		);
	}
	
	/**
		Sets the location of the endpoints of this <code>LineShape</code> to the specified coordinates.
		@param x1,&nbsp;y1 the first specified coordinate
		@param x2,&nbsp;y2 the second specified coordinate
	*/
	public void setLine(int x1, int y1, int x2, int y2) {
		((Line2D.Double)underlyingShape).setLine(x1, y1, x2, y2);
	}
	
	/**
		Sets the location of the endpoints of this <code>LineShape</code> to the specified <code>Point2D</code> objects.
		@param p1,&nbsp;p2 the specified <code>Point2D</code> objects
	*/
	public void setLine(Point2D p1, Point2D p2) {
		((Line2D.Double)underlyingShape).setLine(p1, p2);
	}

	/**
		Creates, initializes with the state read from a <code>BufferedReader</code> stream, and returns a <code>LineShape</code> object.
		@param stream the <code>BufferedReader</code> stream to read the state from
		@return the created and initialized <code>LineShape</code> object.
		@see writeToStream()
	*/
	public Shape readFromStream(BufferedReader stream) {
		int checkColor = 0;													// checks if edge or fill colors are null
		try {
			LineShape temp = new LineShape(									// read location
				Integer.parseInt(stream.readLine()),
				Integer.parseInt(stream.readLine()),
				Integer.parseInt(stream.readLine()),
				Integer.parseInt(stream.readLine())
			);
			
			if( (checkColor = Integer.parseInt(stream.readLine())) == -1)	// read edge color
				temp.setEdgeColor(null);
			else
				temp.setEdgeColor(
					new Color(
						checkColor,
						Integer.parseInt(stream.readLine()),
						Integer.parseInt(stream.readLine())
					)
				);
			
			return temp;
		}
		catch(IOException e) { e.printStackTrace(); }
		
		return null;
	}

	/*
		The underlying Line2D.Double shape.
	*/
	private java.awt.Shape underlyingShape;
	
	/*
		The maximum distance from the line where it is still selectable.
	*/
	private final double DIST_FROM_LINE = 3.0;
}