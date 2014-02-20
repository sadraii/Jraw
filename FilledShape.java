/*	Filename: FilledShape.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/6/02
*/


import java.awt.*;


/**
	The <code>FilledShape</code> class represents shapes that have a fill and edge color in 2D space. The colors are specified through <code>Color</code> objects.
	@see java.awt.Color
*/
abstract public class FilledShape extends EdgedShape implements Cloneable
{
	/**
		Constructs and initializes a <code>FilledShape</code> with the fill and edge colors set to null.
	*/
	public FilledShape() {
		super();
		fill = null;
	}
	
	/**
		Constructs and initializes a <code>FilledShape</code> with the specified fill color.
		@param fill the specified fill color as a <code>Color</code> object.
	*/
	public FilledShape(Color fill) {
		super();
		this.fill = fill;
	}
	
	/**
		Constructs and initializes a <code>FilledShape</code> with the specified edge and fill colors.
		@param edge the specified edge color as a <code>Color</code> object.
		@param fill the specified fill color as a <code>Color</code> object.
	*/
	public FilledShape(Color edge, Color fill) {
		super(edge);
		this.fill = fill;
	}

	// ACCESSORS --------------------------------------------------------------

	/**
		Returns the fill color of this <code>FilledShape</code> as a <code>Color</code> object.
		@return the fill color of this <code>FilledShape</code>.
	*/
	public Color getFillColor() {
		return fill;
	}
	
	// MUTATORS --------------------------------------------------------------
	
	/**
		Sets the fill color of this <code>FilledShape</code> to the specified color.
		@param fill the specified fill color as a <code>Color</code> object.
	*/
	public void setFillColor(Color fill) {
		this.fill = fill;
	}

	/*
		The fill color.
	*/
	private Color fill;
}
