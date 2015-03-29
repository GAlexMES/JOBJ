package jobj.elements;

import java.util.ArrayList;

public class Face implements Element{
	private int smoothingGroupe;

	public Face() {
		smoothingGroupe = 0;
	}

	public void setSmoothingGroupe(int smoothingGroupe) {
		this.smoothingGroupe = smoothingGroupe;
	}

	public void addVertex(Integer id) {
		vertexIDs.add(id);
	}

	public boolean addVertex(String vertexID){
		if(vertexID.contains("/")){
			String[] ids = vertexID.split("/");
			for(int i = 0; i<ids.length;i++){
				try{
					Integer id = Integer.valueOf(ids[i]);
					switch(i){
					case 0: vertexIDs.add(id); break;
					case 1: vertexTextureIDs.add(id); break;
					case 2: vertexNormalIDs.add(id); break;
					}
				}
				catch(NumberFormatException nfe){
				}
			}
		}
		else{
			return false;
		}
		return checkLists();
	}

	private boolean checkLists() {
		int vSize = vertexIDs.size();
		int vnSize = vertexNormalIDs.size();
		int vtSize = vertexTextureIDs.size();

		if (vSize != vnSize && vnSize != 0) {
			return false;
		}
		if (vSize != vtSize && vtSize != 0) {
			return false;
		}
		return true;
	}

	public ArrayList<Integer> getVertexIDs() {
		return vertexIDs;
	}

	public ArrayList<Integer> getVertexNormalIDs() {
		return vertexNormalIDs;
	}

	public ArrayList<Integer> getVertexTextureIDs() {
		return vertexTextureIDs;
	}
	
	
}
