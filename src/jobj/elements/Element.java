package jobj.elements;

import java.util.ArrayList;

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
	 * vertexIDs is used for every Element tag. vertexNormalIDs and vertexTextureIDs is only used for the face tag
	 */
	ArrayList<Integer> vertexIDs = new ArrayList<>();
	ArrayList<Integer> vertexNormalIDs = new ArrayList<>();
	ArrayList<Integer> vertexTextureIDs= new ArrayList<>();
	
	final static int POINT = 0;
	final static int LINE = 1;
	final static int FACE = 2;
	
	
	void addVertex(Integer vertexID);
	int getType();
}
