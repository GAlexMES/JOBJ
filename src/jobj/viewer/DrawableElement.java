package jobj.viewer;

import java.util.ArrayList;

import jobj.datamodel.elements.Element;
import jobj.datamodel.vertex.Vertex;

public class DrawableElement {

	private int type;
	private ArrayList<Vertex> vertices;
	private ArrayList<Vertex> textureVertices;
	private ArrayList<Vertex> normalVertices;

	public DrawableElement(int type) {
		this.type = type;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public void setTextureVertices(ArrayList<Vertex> textureVertices) {
		this.textureVertices = textureVertices;
	}

	public void setNormalVertices(ArrayList<Vertex> normalVertices) {
		this.normalVertices = normalVertices;
	}

	public void draw() {
		switch (type) {
		case Element.POINT:
			drawPoint();
			break;
		case Element.LINE:
			drawLine();
			break;
		case Element.FACE:
			drawFace();
			break;
		}
	}
	
	private void drawPoint(){
		System.out.println("Draw Point at :");
		System.out.println("X: "+ vertices.get(0).getxCoordinate());
		System.out.println("Y: "+ vertices.get(0).getyCoordinate());
		System.out.println("Z: "+ vertices.get(0).getzCoordinate());
	}
	
	private void drawLine(){
		
	}
	
	private void drawFace(){
		
	}
}
