/*	Filename: ShapesList.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/21/02
*/


import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;


class ShapesList
{
	public ShapesList(DrawPanel panel) {
		this.panel = panel;
		shapes = new ArrayList();
		selection = new ArrayList();
//		current = null;
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < shapes.size(); i++)
			((Shape)shapes.get(i)).draw(g);
	}
	
	public void selectionDraw(Graphics g) {
		for(int i = 0; i < selection.size(); i++)
			((Shape)selection.get(i)).draw(g);
	}
	
	public void drawHandles(Graphics g) {
		for (int i = 0; i < selection.size(); i++)
			((Shape)selection.get(i)).drawHandles(g);
	}
	
	public Shape remove(Shape s) {
		return (Shape)shapes.remove(shapes.indexOf(s));
	}
	
	public Shape remove(int i) {
		return (Shape)shapes.remove(i);
	}
	
	public Shape selectionRemove(Shape s) {
		return (Shape)selection.remove(selection.indexOf(s));
	}
	
	public Shape selectionRemove(int i) {
		return (Shape)selection.remove(i);
	}
	
	public boolean contains(Point2D point) {
		for(int i = 0; i < shapes.size(); i++)
			if( ((Shape)shapes.get(i)).contains(point) )
				return true;
		
		return false;
	}
	
	public boolean selectionContains(Point2D point) {
		for(int i = 0; i < selection.size(); i++)
			if( ((Shape)selection.get(i)).contains(point) )
				return true;
		
		return false;
	}
	
	public Shape shapeThatContains(Point2D point) {
		for(int i = 0; i < shapes.size(); i++)
			if( ((Shape)shapes.get(i)).contains(point) )
				return (Shape)shapes.get(i);
				
		return null;
	}
	
	public Shape selectionShapeThatContains(Point2D point) {
		for(int i = 0; i < selection.size(); i++)
			if( ((Shape)selection.get(i)).contains(point) )
				return (Shape)selection.get(i);
				
		return null;
	}
	
	public void repositionBy(int deltaX, int deltaY) {
		for(int i = 0; i < shapes.size(); i++)
			((Shape)shapes.get(i)).translate(deltaX, deltaY);
	}
	
	public void selectionRepositionBy(int deltaX, int deltaY) {
		for(int i = 0; i < selection.size(); i++)
			((Shape)selection.get(i)).translate(deltaX, deltaY);
	}

	/**
		Adds a square to the collection.
		@param p the center of the square
	*/
	public boolean add(Shape s) {
		return shapes.add(s);
	}
	
	public boolean selectionAdd(Shape s) {
		for(int i = 0; i < selection.size(); i++)
			if( (Shape)selection.get(i) == s )
				return false;
		return selection.add(s);
	}
	
	public void add(int i, Shape s) {
		shapes.add(i, s);
	}
	
	public void selectionAdd(int index, Shape s) {
		for(int i = 0; i < selection.size(); i++)
			if( (Shape)selection.get(i) == s)
				return;
		selection.add(index, s);
	}

	public int size() {
		return shapes.size();
	}
	
	public int selectionSize() {
		return selection.size();
	}
	
	public Shape get(int i) {
		return (Shape)shapes.get(i);
	}
	
	public Shape selectionGet(int i) {
		return (Shape)selection.get(i);
	}
	
	public boolean isEmpty() {
		return shapes.isEmpty();
	}
	
	public boolean selectionIsEmpty() {
		return selection.isEmpty();
	}
	
	public void clear() {
		shapes.clear();
	}
	
	public void selectionClear() {
		selection.clear();
	}
	
	public void set(int i, Shape s) {
		shapes.set(i, s);
	}
	
	public void selectionSet(int i, Shape s) {
		selection.set(i, s);
	}
	
	public boolean containsElement(Shape s) {
		return shapes.contains(s);
	}
	
	public boolean selectionContainsElement(Shape s) {
		return selection.contains(s);
	}
	
	private ArrayList shapes;
	private ArrayList selection;
	private DrawPanel panel;
}