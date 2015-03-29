package jobj.jobj;

import java.util.ArrayList;

import jobj.elements.ElementsGroup;

public class Object {

	private ArrayList<ElementsGroup> faceGroupList;
	private String objectName;
	
	public Object(){
		faceGroupList = new ArrayList<>();
	}
	
	public void addFaceGroup(ElementsGroup faceGroup){
		faceGroupList.add(faceGroup);
	}
	
	public void setName(String name){
		objectName = name;
	}
}
