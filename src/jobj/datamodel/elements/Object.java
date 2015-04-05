package jobj.datamodel.elements;

import java.util.ArrayList;

public class Object {

	private ArrayList<ElementsGroup> elementsGroupList;
	private String objectName;
	
	public Object(){
		elementsGroupList = new ArrayList<>();
	}
	
	public void addElementsGroup(ElementsGroup faceGroup){
		elementsGroupList.add(faceGroup);
	}
	
	public void setName(String name){
		objectName = name;
	}
	
	public String getName(){
		return objectName;
	}
	
	public ArrayList<ElementsGroup> getElementsGroup(){
		return this.elementsGroupList;
	}
}
