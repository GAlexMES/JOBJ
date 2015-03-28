package jobj.faces;

import java.util.ArrayList;


public class FaceGroup {

	private ArrayList<Face> faceList;
	private String name;
	private String mtlLibTexture;
	
	public FaceGroup(String name){
		this.name=name;
		faceList = new ArrayList<>();
	}
	
	public void setMTLLibTexture(String textureName){
		mtlLibTexture = textureName;
	}
	
	public void addFace (Face face){
		faceList.add(face);
	}
	
	public ArrayList<Face> getFaceList(){
		return this.faceList;
	}
}
