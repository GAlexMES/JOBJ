package jobj.elements;

import java.util.ArrayList;


public class ElementsGroup{

	private ArrayList<Element> faceList;
	private String name;
	private String mtlLibTexture;
	
	public ElementsGroup(String name){
		this.name=name;
		faceList = new ArrayList<>();
	}
	
	public void setMTLLibTexture(String textureName){
		mtlLibTexture = textureName;
	}
	
	public void addElement (Element elem){
		faceList.add(elem);
	}
	
	public ArrayList<Element> getElementsList(){
		return this.faceList;
	}
}
