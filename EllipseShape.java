/*	Filename: EllipseShape.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import java.io.*;
import java.awt.*;
import java.awt.geom.*;

/**
	The <code>EllipseShape</code> class represents an Ellipse in 2D space specified in integer coordinates. It also contains an edge and fill color specified with a <code>Color</code> object.
	@see java.awt.geom.Ellipse2D.Double
	@see java.awt.Color
*/
public class EllipseShape extends FilledShape implements Cloneable
{
	/**
		Constructs an <code>EllipseShape</code>, initialized to location (0, 0) and with size (0, 0).
	*/
	public EllipseShape() {
		super();
		underlyingShape = new Ellipse2D.Double();
	}
	
	/**
		Constructs and initializes an <code>EllipseShape</code> from the specified integer coordinates.
		@param x,&nbsp;y the coordinates of the upper left corner
		@param w the width
		@param h the height
	*/
	public EllipseShape(int x, int y, int w, int h) {
		super();
		underlyingShape = new Ellipse2D.Double(x, y, w, h);
	}
	
	/**
		Constructs and initializes a <code>EllipseShape</code> from the specified integer coordinates and edge and fill colors.
		@param x,&nbsp;y the coordinates of the upper left corner
		@param w the width
		@param h the height
		@param edge the edge color
		@param fill the fill color
	*/
	public EllipseShape(int x, int y, int w, int h, Color edge, Color fill) {
		super(edge, fill);
		underlyingShape = new Ellipse2D.Double(x, y, w, h);
	}
	
	// ACCESSORS --------------------------------------------------------------
	
	/**
		Returns the underlying <code>Ellipse2D.Double</code> shape.
		@return the underlying <code>Ellipse2D.Double</code> shape.
		@see java.awt.geom.Ellipse2D.Double
	*/
	protected java.awt.Shape getUnderlyingShape() {
		return underlyingShape;
	}
	
	/**
		Returns the X coordinate of this <code>EllipseShape</code> object's upper left corner in integer precision.
		@return the X coordinate of this <code>EllipseShape</code> object's upper left corner.
	*/
	public int getX() {
		return (int)((Ellipse2D.Double)underlyingShape).getX();
	}
	
	/**
		Returns the Y coordinate of this <code>EllipseShape</code> object's upper left corner in integer precision.
		@return the Y coordinate of this <code>EllipseShape</code> object's upper left corner.
	*/
	public int getY() {
		return (int)((Ellipse2D.Double)underlyingShape).getY();
	}
	
	/**
		Returns the width of this <code>EllipseShape</code> in integer precision.
		@return the width of this <code>EllipseShape</code>.
	*/
	public int getWidth() {
		return (int)((Ellipse2D.Double)underlyingShape).width;
	}
	
	/**
		Returns the height of this <code>EllipseShape</code> in integer precision.
		@return the height of this <code>EllipseShape</code>.
	*/
	public int getHeight() {
		return (int)((Ellipse2D.Double)underlyingShape).height;
	}
	
	/**
		Writes the object's state to a <code>PrintWriter</code> stream.
		The state is written in the following manner (without any of the comments or the "----"):
		----
		EllipseShape				// the class name
		<int>						// the x coordinate
		<int>						// the y coordinate
		<int>						// the width
		<int>						// the height
		<int>						// the red component of the edge color
		<int>						// the green component of the edge color*
		<int>						// the blue component of the edge color*
		<int>						// the red component of the fill color
		<int>						// the green component of the fill color*
		<int>						// the blue component of the fill color*
		----
		* if the edge or fill color is null, the red component is replaced with "-1" and the green and blue components are not written out.
		@param stream the <code>PrintWriter</code> stream to write the state to
	*/
	public void writeToStream(PrintWriter stream) {
		stream.println(													// write location and size
			getClass().getName() + "\n" +
			(int)((Ellipse2D.Double)underlyingShape).x + "\n" +
			(int)((Ellipse2D.Double)underlyingShape).y + "\n" +
			(int)((Ellipse2D.Double)underlyingShape).width + "\n" +
			(int)((Ellipse2D.Double)underlyingShape).height
		);
		
		if(getEdgeColor() == null)										// write edge color
			stream.println("-1");
		else
			stream.println(
				getEdgeColor().getRed() + "\n" +
				getEdgeColor().getGreen() + "\n" +
				getEdgeColor().getBlue()
			);
			
		if(getFillColor() == null)										// write fill color
			stream.println("-1");
		else
			stream.println(
				getFillColor().getRed() + "\n" +
				getFillColor().getGreen() + "\n" +
				getFillColor().getBlue()
			);
	}
	
	/**
		Draws the handles of the <code>EllipseShape</code> using a <code>Graphics</code> object.
		@param g the <code>Graphics</code> object
	*/
	protected void drawHandles(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		
		// draws the handles in clockwise order, starting in the upper left corner
		g2.drawRect(getX() - HANDLE_SIZE/2, getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
		g2.drawRect(getX() + getWidth() - HANDLE_SIZE/2, getY() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
		g2.drawRect(getX() + getWidth() - HANDLE_SIZE/2, getY() + getHeight() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
		g2.drawRect(getX() - HANDLE_SIZE/2, getY() + getHeight() - HANDLE_SIZE/2, HANDLE_SIZE, HANDLE_SIZE);
	}
	
	/**
		Creates a new object of the same class with the same contents as this object.
		@return a clone of this instance.
		@throws OutOfMemoryError if there is not enough memory.
		@see java.lang.Cloneable
	*/
	public Object clone() {
		EllipseShape cloned = (EllipseShape)super.clone();
//		cloned.underlyingShape = (java.awt.Shape)underlyingShape.clone();
		
		return cloned;
	}
	
	/**
		Returns true if the shape contains the <code>Point2D</code> object; false otherwise.
		@param point the <code>Point2D</code> object
		@return true if the shape contains the point; false otherwise.
	*/
	public boolean contains(Point2D point) {
		return ((Ellipse2D.Double)underlyingShape).contains(point);
	}
	
	/**
		Returns a high precision bounding box of the <code>EllipseShape</code> object.
		@return a high precision bounding box of the <code>EllipseShape</code> object.
	*/
	public Rectangle2D getBounds2D() {
		return ((Ellipse2D.Double)underlyingShape).getBounds2D();
	}
	
	/**
		Returns a <code>String</code> representation of the object. 
		@return a <code>String</code> representation of the object.
	*/
	public String toString() {
		return getClass().getName() + "[("
		+ "x=" + ((Ellipse2D.Double)underlyingShape).x + ", "
		+ "y=" + ((Ellipse2D.Double)underlyingShape).y + "), "
		+ "width=" + ((Ellipse2D.Double)underlyingShape).width + ", "
		+ "height=" + ((Ellipse2D.Double)underlyingShape).height + ", "
		+ "edgeColor=" + getEdgeColor() + ", "
		+ "fillColor=" + getFillColor() + "]";
	}
	
	// MUTATORS --------------------------------------------------------------
	
	/**
		Translates the location of the <code>EllipseShape</code> by the specified integer coordinates.
		@param deltaX the amount to translate along the <i>x</i> axis
		@param deltaY the amount to translate along the <i>y</i> axis
	*/
	public void translate(int deltaX, int deltaY) {
		setFrame(
			getX() + deltaX,
			getY() + deltaY,
			getWidth(),
			getHeight()
		);
	}	
	
	/**
		Sets the location and size of this <code>EllipseShape</code> to the specified integer values.
		@param x,&nbsp;y the coordinate of the upper left corner
		@param w the width
		@param h the height
	*/
	public void setFrame(int x, int y, int w, int h) {
		((Ellipse2D.Double)underlyingShape).setFrame(x, y, w, h);
	}
	
	/**
		Creates, initializes with the state read from a <code>BufferedReader</code> stream, and returns an <code>EllipseShape</code> object.
		@param stream the <code>BufferedReader</code> stream to read the state from
		@return the created and initialized <code>EllipseShape</code> object.
		@see writeToStream()
	*/
	public Shape readFromStream(BufferedReader stream) {
		int checkColor = 0;													// checks if edge or fill colors are null
		try {
			EllipseShape temp = new EllipseShape(							// read location and size
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
			
			if( (checkColor = Integer.parseInt(stream.readLine())) == -1)	// read fill color
				temp.setFillColor(null);
			else
				temp.setFillColor(
					new Color(
						checkColor,
						Integer.parseInt(stream.readLine()),
						Integer.parseInt(stream.readLine())
					)
				);
			
			return temp;
					
		/*	return new EllipseShape(
				Integer.parseInt(stream.readLine()),
				Integer.parseInt(stream.readLine()),
				Integer.parseInt(stream.readLine()),
				Integer.parseInt(stream.readLine()),
				new Color(
					Integer.parseInt(stream.readLine()),
					Integer.parseInt(stream.readLine()),
					Integer.parseInt(stream.readLine())
				),
				new Color(
					Integer.parseInt(stream.readLine()),
					Integer.parseInt(stream.readLine()),
					Integer.parseInt(stream.readLine())
				)
			);*/
		}
		catch(IOException e) { e.printStackTrace(); }
		
		return null;
	}
	
	/*
		The underlying Ellipse2D.Double shape.
	*/
	private java.awt.Shape underlyingShape;
}