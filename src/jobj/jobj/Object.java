package jobj.jobj;

import java.util.ArrayList;

import jobj.faces.FaceGroup;

public class Object {

	private ArrayList<FaceGroup> faceGroupList;
	private String objectName;
	
	public Object(){
		faceGroupList = new ArrayList<>();
	}
	
	public void addFaceGroup(FaceGroup faceGroup){
		faceGroupList.add(faceGroup);
	}
	
	public void setName(String name){
		objectName = name;
	}
}
