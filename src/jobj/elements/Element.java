package jobj.elements;

import java.util.ArrayList;

import jobj.vertex.Vertex;

/**
 * <h1> Element Interface </h1>
 * Is used for the following .obj tags:
 * <ul> p - Point </ul>
 * <ul> l - Line </ul>
 * <ul> f - Face </ul>
 * @author Alexander Brennecke
 *
 */
public interface Element {
	
	/**
	 * Defines the type of the class, which implements this interface.
	 * Used for clearly identification.
	 */
	final static int POINT = 0;
	final static int LINE = 1;
	final static int FACE = 2;
	
	
	void addVertex(Integer vertexID);
	ArrayList<Integer> getVertexIDs();
	
	int getType();
}
