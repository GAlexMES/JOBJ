package jobj.faces;

import java.util.ArrayList;

public class Face {
	
	private ArrayList<Integer> vertexIDs;
	private int smoothingGroupe;
	
	public Face(){
		vertexIDs = new ArrayList<>();
		smoothingGroupe = 0;
	}
	
	public void setSmoothingGroupe(int smoothingGroupe){
		this.smoothingGroupe=smoothingGroupe;
	}
	
	public void addVertex(Integer id){
		vertexIDs.add(id);
	}

}
