package jobj.viewer;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

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

	private void drawPoint() {
		Vertex vertex = vertices.get(0);
		GL11.glBegin(GL11.GL_POINTS);
		float x = vertex.getxCoordinate().floatValue();
		float y = vertex.getyCoordinate().floatValue();
		float z = vertex.getzCoordinate().floatValue();
		GL11.glVertex3f(x/10,y/10,z/10);
		GL11.glEnd();
	}

	private void drawLine() {
		GL11.glBegin(GL11.GL_LINE_STRIP);

		for(Vertex ver : vertices){
			float x = ver.getxCoordinate().floatValue();
			float y = ver.getyCoordinate().floatValue();
			float z = ver.getzCoordinate().floatValue();
			GL11.glVertex2d(x/10,y/10);
		}
		
		GL11.glEnd();
	}

	private void drawFace() {
		GL11.glBegin(GL11.GL_POLYGON);
		
		for(Vertex ver : vertices){
			float x = ver.getxCoordinate().floatValue();
			float y = ver.getyCoordinate().floatValue();
			float z = ver.getzCoordinate().floatValue();
			GL11.glVertex3f(x/10,y/10,z/10);
		}

		GL11.glEnd();
	}
}
