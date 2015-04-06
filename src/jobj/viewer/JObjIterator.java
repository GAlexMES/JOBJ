package jobj.viewer;

import java.util.ArrayList;

import jobj.datamodel.elements.Element;
import jobj.datamodel.elements.ElementsGroup;
import jobj.datamodel.elements.Face;
import jobj.datamodel.elements.Line;
import jobj.datamodel.elements.Object;
import jobj.datamodel.elements.Point;
import jobj.datamodel.jobj.JObj;
import jobj.datamodel.vertex.Vertex;
import jobj.datamodel.vertex.Vertices;

public class JObjIterator {

	private JObj jobj;
	
	public JObjIterator(JObj jobj){
		this.jobj = jobj;
	}
	
	public JObj getJObj(){
		return this.jobj;
	}

	public void generateView(){
		ArrayList<Object> objectList = jobj.getObjects();
		for(Object obj : objectList){
			handleObject(obj);
		}
	}
	
	private void handleObject (Object obj){
		ArrayList<ElementsGroup> elemGroups = obj.getElementsGroup();
		for(ElementsGroup elemGroup : elemGroups){
			handleElementsGroup(elemGroup);
		}
	}
	
	private void handleElementsGroup(ElementsGroup elemGroup){
		ArrayList<Element> elements = elemGroup.getElementsList();
		ArrayList<Integer> vertexIDs;
		Vertices vertices = jobj.getVerticies();
		for(Element elem : elements){
			switch(elem.getType()){
			case Element.POINT:
				int vertexID = ((Point)elem).getVertex();
				Vertex pointVertex = vertices.getVertex(vertexID, Vertices.VERTEX);
				drawPoint(pointVertex);
				break;
			case Element.LINE:
				vertexIDs = ((Line)elem).getVertexIDs();
				ArrayList<Vertex> lineVertices = vertices.getMultipleIDs(vertexIDs,  Vertices.VERTEX);
				drawLine(lineVertices);
				break;
			case Element.FACE:
				vertexIDs = ((Face)elem).getVertexIDs();
				ArrayList<Vertex> faceVertices = vertices.getMultipleIDs(vertexIDs, Vertices.VERTEX);
				vertexIDs = ((Face)elem).getVertexTextureIDs();
				ArrayList<Vertex> faceTextureVertices = vertices.getMultipleIDs(vertexIDs, Vertices.TEXTURE_VERTEX);
				vertexIDs = ((Face)elem).getVertexNormalIDs();
				ArrayList<Vertex> faceNormalsVertices = vertices.getMultipleIDs(vertexIDs, Vertices.NORMALS_VERTEX);
				drawFace(faceVertices, faceTextureVertices, faceNormalsVertices);
				break;
			}
		}
	}
	
}
