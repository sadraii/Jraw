/*	Filename: PolygonShape.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import java.io.*;
import java.awt.*;
import java.awt.geom.*;


/**
	The <code>PolygonShape</code> class represents a Polygon in 2D space specified in integer coordinates. It encapsulates a description of a closed, 2D region within a coordinate space. It also contains an edge and fill color specified with a <code>Color</code> object.
	@see java.awt.Polygon
	@see java.awt.Color
*/
public class PolygonShape extends FilledShape implements Cloneable
{
	/**
		Constructs an empty <code>PolygonShape</code>.
	*/
	public PolygonShape() {
		super();
		underlyingShape = new Polygon();
	}
	
	/**
		Constructs and initializes a <code>PolygonShape</code> from the specified parameters.
		@param xPoints an array of <i>x</i> coordinates
		@param yPoints an array of <i>y</i> coordinates
		@param nPoints the total number of points in the <code>PolygonShape</code>
	*/
	public PolygonShape(int[] xPoints, int[] yPoints, int nPoints) {
		super();
		underlyingShape = new Polygon(xPoints, yPoints, nPoints);
	}
	
	/**
		Constructs and initializes a <code>PolygonShape</code> from the specified parameters.
		@param xPoints an array of <i>x</i> coordinates
		@param yPoints an array of <i>y</i> coordinates
		@param nPoints the total number of points in the <code>PolygonShape</code>
		@param edge the edge color
		@param fill the fill color
	*/
	public PolygonShape(int[] xPoints, int[] yPoints, int nPoints, Color edge, Color fill) {
		super(edge, fill);
		underlyingShape = new Polygon(xPoints, yPoints, nPoints);
	}
	
	// ACCESSORS --------------------------------------------------------------
	
	/**
		Returns the underlying <code>Polygon</code> shape.
		@return the underlying <code>Polygon</code> shape.
		@see java.awt.Polygon
	*/
	protected java.awt.Shape getUnderlyingShape() {
		return underlyingShape;
	}

	/**
		Writes the object's state to a <code>PrintWriter</code> stream.
		The state is written in the following manner (without any of the comments or the "----"):
		----
		PolygonShape				// the class name
		<int>						// the number of vertices
		<int>[]						// the array of x coordinates*
		<int>[]						// the array of y coordinates*
		<int>						// the red component of the edge color
		<int>						// the green component of the edge color**
		<int>						// the blue component of the edge color**
		<int>						// the red component of the fill color
		<int>						// the green component of the fill color**
		<int>						// the blue component of the fill color**
		----
		* the arrays are written out as one element per line
		** if the edge or fill color is null, the red component is replaced with "-1" and the green and blue components are not written out.
		@param stream the <code>PrintWriter</code> stream to write the state to
	*/
	public void writeToStream(PrintWriter stream) {
		stream.println(															// write number of vertices
			getClass().getName() + "\n" +
			((Polygon)underlyingShape).npoints
		);
		
		for(int i = 0; i < ((Polygon)underlyingShape).xpoints.length; i++)		// write array of x coords
			stream.println(((Polygon)underlyingShape).xpoints[i]);
			
		for(int i = 0; i < ((Polygon)underlyingShape).ypoints.length; i++)		// write array of y coords
			stream.println(((Polygon)underlyingShape).ypoints[i]);
			
		if(getEdgeColor() == null)												// write edge color
			stream.println("-1");
		else
			stream.println(
				getEdgeColor().getRed() + "\n" +
				getEdgeColor().getGreen() + "\n" +
				getEdgeColor().getBlue()
			);
			
		if(getFillColor() == null)												// write fill color
			stream.println("-1");
		else
			stream.println(
				getFillColor().getRed() + "\n" +
				getFillColor().getGreen() + "\n" +
				getFillColor().getBlue()
			);
	}
	
	/**
		Draws the handles of the <code>PolygonShape</code> using a <code>Graphics</code> object.
		@param g the <code>Graphics</code> object
	*/
	protected void drawHandles(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		
		for(int i = 0; i < ((Polygon)underlyingShape).npoints; i++)
			g2.drawRect(
				((Polygon)underlyingShape).xpoints[i] - HANDLE_SIZE/2,
				((Polygon)underlyingShape).ypoints[i] - HANDLE_SIZE/2,
				HANDLE_SIZE,
				HANDLE_SIZE
			);
	}
	
	/**
		Creates a new object of the same class with the same contents as this object.
		@return a clone of this instance.
		@throws OutOfMemoryError if there is not enough memory.
		@see java.lang.Cloneable
	*/
	public Object clone() {
		PolygonShape cloned = (PolygonShape)super.clone();
//		cloned.underlyingShape = (java.awt.Shape)underlyingShape.clone();
		
		return cloned;
	}
	
	public boolean contains(Point2D point) {
		return ((Polygon)underlyingShape).contains(point);
	}
	
	/**
		Returns a high precision bounding box of the <code>PolygonShape</code> object.
		@return a high precision bounding box of the <code>PolygonShape</code> object.
	*/
	public Rectangle2D getBounds2D() {
		return ((Polygon)underlyingShape).getBounds2D();
	}
	
	/**
		Returns a <code>String</code> representation of the object. 
		@return a <code>String</code> representation of the object.
	*/
	public String toString() {
		String ret = getClass().getName() + "[{";
		for(int i = 0; i < ((Polygon)underlyingShape).xpoints.length; i++) {
			ret += ((Polygon)underlyingShape).xpoints[i];
			if(i + 1 != ((Polygon)underlyingShape).xpoints.length)
				ret += ", ";
			else
				ret += "}, { ";
		}
		for(int i = 0; i < ((Polygon)underlyingShape).ypoints.length; i++) {
			ret += ((Polygon)underlyingShape).ypoints[i];
			if(i + 1 != ((Polygon)underlyingShape).ypoints.length)
				ret += ", ";
			else
				ret += "}, ";
		}
		ret += ((Polygon)underlyingShape).npoints + ", " + "edgeColor=" + getEdgeColor() + ", " + "fillColor=" + getFillColor() + "]";
		return ret;
	}
	
	// MUTATORS --------------------------------------------------------------

	/**
		Translates the vertices of the <code>PolygonShape</code> by the specified integer coordinates.
		@param deltaX the amount to translate along the <i>x</i> axis
		@param deltaY the amount to translate along the <i>y</i> axis
	*/
	public void translate(int deltaX, int deltaY) {
		((Polygon)underlyingShape).translate(deltaX, deltaY);
	}
	
	/**
		Appends the specified coordinates to this <code>PolygonShape</code>.
		@param x,&nbsp;y the specified coordinates
	*/
	public void addPoint(int x, int y) {
		((Polygon)underlyingShape).addPoint(x, y);
	}
	
	/**
		Creates, initializes with the state read from a <code>BufferedReader</code> stream, and returns a <code>PolygonShape</code> object.
		@param stream the <code>BufferedReader</code> stream to read the state from
		@return the created and initialized <code>PolygonShape</code> object.
		@see writeToStream()
	*/
	public Shape readFromStream(BufferedReader stream) {
		int checkColor = 0;
		try {
			int nPoints = Integer.parseInt(stream.readLine());			// number of vertices
			int[] xPoints = new int[nPoints];							// array of x coords
			int[] yPoints = new int[nPoints];							// array of y coords
			
			for(int i = 0; i < nPoints; i++)							// read x coords
				xPoints[i] = Integer.parseInt(stream.readLine());
			for(int i = 0; i < nPoints; i++)							// read y coords
				yPoints[i] = Integer.parseInt(stream.readLine());
			
			PolygonShape temp = new PolygonShape(
				xPoints,
				yPoints,
				nPoints
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
		}
		catch(IOException e) { e.printStackTrace(); }
		
		return null;
	}
		
	/*
		The underlying Polygon shape.
	*/
	private java.awt.Shape underlyingShape;
}