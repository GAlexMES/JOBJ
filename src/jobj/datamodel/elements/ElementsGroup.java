package jobj.datamodel.elements;

import java.util.ArrayList;

/**
 * <h1> Elements Group </h1>
 * This class is for the "g" tag in .obj files.
 * It stores all elements, including "f", "p", and "l".
 * Also it could contain a mtlLib texture
 * @author Alexander Brennecke
 *
 */
public class ElementsGroup{

	
	private ArrayList<Element> faceList;
	private String name;
	private String mtlLibTexture;
	
	/**
	 * Constructor
	 * @param name The name of the group.
	 */
	public ElementsGroup(String name){
		this.name=name;
		faceList = new ArrayList<>();
	}
	
	/**
	 * Must be set, if the group should be displayed with a texture.
	 * @param textureName The name of the texture in the .mtl file.
	 */
	public void setMTLLibTexture(String textureName){
		mtlLibTexture = textureName;
	}
	
	/**
	 * Adds a Element ("f", "p", or "l") to the group.
	 * @param elem The Element which should be added.
	 */
	public void addElement (Element elem){
		faceList.add(elem);
	}
	
	public ArrayList<Element> getElementsList(){
		return this.faceList;
	}

	public String getName() {
		return name;
	}
}
