/*	Filename: Shape.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import java.io.*;
import java.awt.*;
import java.awt.geom.*;


/**
	An abstract class that represents general shapes.
*/
abstract public class Shape implements DrawableElement, Cloneable
{
	/**
		Constructs a <code>Shape</code>.
	*/
	public Shape() {
		super();
	}
	
	// ACCESSORS --------------------------------------------------------------
	
	/**
		Returns null. This method is meant to be overriden in a subclass, if that subclass supports an edge color as a <code>Color</code> object.
		@return null.
	*/
	public Color getEdgeColor() { return null; }
	
	/**
		Returns null. This method is meant to be overriden in a subclass, if that subclass supports a fill color as a <code>Color</code> object.
		@return null.
	*/
	public Color getFillColor() { return null; }
	
	/**
		Returns the underlying <code>java.awt.Shape</code> shape.
		@return the underlying <code>java.awt.Shape</code> shape.
		@see java.awt.Shape
	*/
	abstract protected java.awt.Shape getUnderlyingShape();		//could be public?
	
	/**
		Writes this object's state to a <code>PrintWriter</code> stream.
		@param stream the <code>PrintWriter</code> stream to write the state to
	*/
	abstract public void writeToStream(PrintWriter out);
	
	/**
		Draws the current <code>Shape</code> using a <code>Graphics</code> object.
		@param g the <code>Graphics</code> object
	*/
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		if(getFillColor() != null) {
			g2.setColor(getFillColor());
			g2.fill(getUnderlyingShape());
		}
		g2.setColor(getEdgeColor());
		g2.draw(getUnderlyingShape());
	}
	
	/**
		Draws the handles of the current <code>Shape</code> using a <code>Graphics</code> object.
		@param g the <code>Graphics</code> object
	*/
	abstract protected void drawHandles(Graphics g);
	
	/**
		Creates a new object of the same class with the same contents as this object.
		@return a clone of this instance.
		@throws OutOfMemoryError if there is not enought memory.
		@see java.lang.Cloneable
	*/
	public Object clone() {
		try {
			return super.clone();
		}
		catch(CloneNotSupportedException e) {					// this shouldn't happen since we are Cloneable
			throw new InternalError();
		}
	}
	
	/**
		Returns true if the shape contains the <code>Point2D</code> object; false otherwise.
		@param point the <code>Point2D</code> object
		@return true if the shape contains the point; false otherwise.
	*/
	abstract public boolean contains(Point2D point);
	
	/**
		Returns a high precision bounding box of the <code>Shape</code> object.
		@return a high precision bounding box of the <code>Shape</code> object.
	*/
	abstract public Rectangle2D getBounds2D();

	// MUTATORS --------------------------------------------------------------
	
	/**
		Translates the location of the <code>Shape</code> by the specified integer coordinates.
		@param deltaX the amount to translate along the <i>x</i> axis
		@param deltaY the amount to translate along the <i>y</i> axis
	*/
	abstract public void translate(int deltaX, int deltaY);
	
	/**
		This method does nothings. It is meant to be overriden in a subclass, if that subclass supports an edge color as a <code>Color</code> object.
	*/
	public void setEdgeColor(Color s) {}
	
	/**
		This method does nothings. It is meant to be overriden in a subclass, if that subclass supports a fill color as a <code>Color</code> object.
	*/
	public void setFillColor(Color s) {}
	
	/**
		Creates, initializes with the state read from a <code>BufferedReader</code> stream, and returns a <code>Shape</code> object.
		@param stream the <code>BufferedReader</code> stream to read the state from
		@return the created and initialized <code>Shape</code> object.
	*/
	abstract public Shape readFromStream(BufferedReader in);
	
	// FIELDS --------------------------------------------------------------
	
	protected static final int HANDLE_SIZE = 2;
}
