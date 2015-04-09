package jobj.viewer;

import java.util.ArrayList;

import jobj.datamodel.elements.Element;
import jobj.datamodel.elements.ElementsGroup;
import jobj.datamodel.elements.Face;
import jobj.datamodel.elements.Object;
import jobj.datamodel.jobj.JObj;
import jobj.datamodel.vertex.Vertex;
import jobj.datamodel.vertex.Vertices;

/**
 * <h1> JObjIterator <h1>
 * 
 * This class iterates over a JObj object and draws the elements in a correct order.
 * @author Alexander Brennecke
 *
 */
public class JObjIterator {
	
	private JObj jobj;
	
	/**
	 * Constructor
	 * @param jobj The JObj object, that should be iterated.
	 */
	public JObjIterator(JObj jobj){
		this.jobj = jobj;
	}
	
	public JObj getJObj(){
		return this.jobj;
	}

	/**
	 * This method iterates over all Objects in the JObj object.
	 */
	public void render(){
		ArrayList<Object> objectList = jobj.getObjects();
		for(Object obj : objectList){
			handleObject(obj);
		}
	}
	
	/**
	 * This method iterates over all ElementsGroups in the given Object.
	 * @param obj the Object, that should be iterated.
	 */
	private void handleObject (Object obj){
		ArrayList<ElementsGroup> elemGroups = obj.getElementsGroup();
		for(ElementsGroup elemGroup : elemGroups){
			handleElementsGroup(elemGroup);
		}
	}
	
	/**
	 * This method iterates over the given ElementsGroup and draws each Element inside.
	 * @param elemGroup The ElementsGroup, that should be iterated.
	 */
	private void handleElementsGroup(ElementsGroup elemGroup){
		ArrayList<Element> elements = elemGroup.getElementsList();
		Vertices vertices = jobj.getVerticies();
		for(Element elem : elements){
			elem.draw(vertices);
		}
	}
	
}
