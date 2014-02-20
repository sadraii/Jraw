/*	Filename: EdgedShape.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/13/02
*/


import java.awt.*;


/**
	The <code>EdgedShape</code> class represents shapes that have an edge color in 2D space. The color is specified through a <code>Color</code> object.
	@see java.awt.Color
*/
abstract public class EdgedShape extends Shape implements Cloneable
{
	/**
		Constructs and initializes an <code>EdgedShape</code> with the edge color set to null.
	*/
	public EdgedShape() {
		super();
		edge = null;
	}
	
	/**
		Constructs and initializes an <code>EdgedShape</code> with the specified edge color.
		@param edge the specified edge color as a <code>Color</code> object.
	*/
	public EdgedShape(Color edge) {
		super();
		this.edge = edge;
	}
	
	// ACCESSORS --------------------------------------------------------------
	
	/**
		Returns the edge color of this <code>EdgedShape</code> as a <code>Color</code> object.
		@return the edge color of this <code>EdgedShape</code>.
		NOTE changed to public - 5/13/02
	*/
	public Color getEdgeColor() {
		return edge;
	}
	
	// MUTATORS --------------------------------------------------------------
	
	/**
		Sets the edge color of this <code>EdgedShape</code> to the specified color.
		@param edge the specified edge color as a <code>Color</code> object.
		NOTE changed to public - 5/13/02
	*/
	public void setEdgeColor(Color edge) {
		this.edge = edge;
	}
	
	/*
		The edge color.
	*/
	private Color edge;
}
