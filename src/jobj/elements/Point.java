package jobj.elements;

import java.util.ArrayList;

public class Point implements Element{
	
	private ArrayList<Integer> vertexIDs;

	public Point(){
		vertexIDs = new ArrayList<>();
	}
	
	@Override
	public void addVertex(Integer vertexID) {
		if(vertexIDs.size()>0){
		vertexIDs.set(0, vertexID);
		}
		else{
			vertexIDs.add(vertexID);
		}
	}
	
	public Integer getVertex(){
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
