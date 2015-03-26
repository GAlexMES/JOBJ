package jobj.faces;

import java.util.ArrayList;

public class Face {
	
	private ArrayList<Integer> vertexIDs;
	
	public Face(){
		vertexIDs = new ArrayList<>();
	}
	
	public void addVertex(Integer id){
		vertexIDs.add(id);
	}

}
