/*	Filename: ShapeRegistry.java
	Author: Mostafa Sadraii <mosadraii@hotmail.com>
	Date modified: 5/13/02
*/


import java.util.*;


/**
	This is a registry class for shape classes.
*/
public class ShapeRegistry
{
	/*	***should be kept private -- no public comment
		Constructs and initialzes a <code>ShapeRegistry</code>.
	*/
	private ShapeRegistry() {
		super();
		registry = new HashMap();
	}

	// ACCESSORS --------------------------------------------------------------
	
	/**
		Returns a clone <code>Shape</code> using the key name, if one is found.
		@return a clone <code>Shape</code> using the key name, if one is found.
		@param name the key name
		@see Shape
	*/
	public Shape get(String name) {
		Shape clonedShape = null;
		if( registry.containsKey(name) )
			 clonedShape = (Shape)(((Shape)registry.get(name)).clone());
		return clonedShape;
	}
	
	/**
		Prints all of the keys in this registry to the console.
	*/
	public void showKeys() {
		Set set = new HashSet();
		Object[] keys = null;
		set = registry.keySet();
		keys = set.toArray();
		for(int i = 0; i < keys.length; i++)
			System.out.println(i + "\t" + (String)keys[i]);
	}

	// MUTATORS --------------------------------------------------------------

	/**
		Allows only one reference of the <code>ShapeRegistry</code> class to be creating by calling a private constructor.
		@return a reference to the single instance of <code>ShapeRegistry</code>.
	*/
	public static ShapeRegistry instance() {
		if(uniqueInstance == null) {
			uniqueInstance = new ShapeRegistry();
		}
		return uniqueInstance;
	}

	/**
		Registers a key and a corresponding <code>Shape</code> with the registry.
		@param name the key to register
		@param shape a corresponding <code>Shape</code>
	*/
	public void register(String name, Shape shape) {
		if(!registry.containsKey(name))
			registry.put(name, shape);
	}
	
	/**
		Registers a <code>Shape</code> with the registry. The <code>Shape</code>'s class name is automatically used as the corresponing key.
		@param the <code>Shape</code> to register
	*/
	public void register(Shape shape) {
		if(!registry.containsKey(shape.getClass().getName()))
			registry.put(shape.getClass().getName(), shape);
	}

	/**
		Removes a <code>Shape</code> based on a key string.
		@param name the key string
	*/
	public void unregister(String name) {
		registry.remove(name);
	}

	/**
		Clears the sole <code>ShapeRegistry</code> instance and all of its registered objects.
	*/
	public void clearRegistry() {
		registry = null;
		uniqueInstance = null;
	}
	
	// FIELDS --------------------------------------------------------------
	
	/*
		The instance of the ShapeRegistry.
	*/
	private static ShapeRegistry uniqueInstance = null;
	
	/*
		The map used for the registered objects.
	*/
	private static HashMap registry;
}