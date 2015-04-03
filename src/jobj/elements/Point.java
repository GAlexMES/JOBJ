package jobj.elements;

import java.util.ArrayList;

/**
 * <h1>Point</h1> This class handles the "p" Point tag in .obj files. It saves
 * exactly one vertex in an array for reasons of easier handeling
 * 
 * @author Alexander Brennecke
 *
 */
public class Point implements Element {

	private ArrayList<Integer> vertexIDs;

	public Point() {
		vertexIDs = new ArrayList<>();
	}

	/**
	 * Sets the first element in the vertexIDs list to the given Integer.
	 */
	@Override
	public void addVertex(Integer vertexID) {
		if (vertexIDs.size() > 0) {
			vertexIDs.set(0, vertexID);
		} else {
			vertexIDs.add(vertexID);
		}
	}

	public Integer getVertex() {
		return vertexIDs.get(0);
	}

	@Override
	public int getType() {
		return Element.POINT;
	}

	@Override
	public ArrayList<Integer> getVertexIDs() {
		return vertexIDs;
	}

}
