package jobj.elements;

import java.util.ArrayList;

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
