/*	Filename: DrawableElement.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 4/28/02
*/


import java.awt.*;


/**
	The <code>DrawableElement</code> class represents any element that is drawable through the <code>Graphics</code> class.
	@see java.awt.Graphics
*/
interface DrawableElement
{
	// ACCESSORS --------------------------------------------------------------

	/**
		Draws the current element using a <code>Graphics</code> object.
	*/
	public void draw (Graphics g);
}