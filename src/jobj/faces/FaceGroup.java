package jobj.faces;

import java.util.ArrayList;

public class FaceGroup {

	private ArrayList<Face> faceList;
	
	public FaceGroup(){
		faceList = new ArrayList<>();
	}
	
	public void addFace (Face face){
		faceList.add(face);
	}
	
	public ArrayList<Face> getFaceList(){
		return this.faceList;
	}
}
