package jobj.elements;

import java.util.ArrayList;

/**
 * <h1> Line </h1>
 * This class handles the "l" Line tag in .obj files.
 * It saves a number of verticies.
 * @author Alexander Brennecke
 *
 */
public class Line implements Element {

	private ArrayList<Integer> vertexIDs;
	
	public Line(){
		vertexIDs = new ArrayList<>();
	}
	
	@Override
	public void addVertex(Integer vertexID) {
		vertexIDs.add(vertexID);
	}

	@Override
	public int getType() {
		return Element.LINE;
	}

	@Override
	public ArrayList<Integer> getVertexIDs() {
		return vertexIDs;
	}


}
