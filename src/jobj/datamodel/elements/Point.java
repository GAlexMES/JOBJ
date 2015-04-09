package jobj.datamodel.elements;

import java.util.ArrayList;

import jobj.datamodel.vertex.Vertex;
import jobj.datamodel.vertex.Vertices;

import org.lwjgl.opengl.GL11;

/**
 * <h1>Point</h1> This class handles the "p" Point tag in .obj files. It saves
 * exactly one vertex in an array for reasons of easier handeling
 * 
 * @author Alexander Brennecke
 *
 */
public class Point implements Element {

	private ArrayList<Integer> vertexIDs;
	private ArrayList<Vertex> vertices;

	public Point() {
		vertexIDs = new ArrayList<>();
	}

	/**
	 * Sets the first element in the vertexIDs list to the given Integer.
	 */
	@Override
	public void addVertex(Integer vertexID) {
		if (vertexIDs.size() > 0) {
			vertexIDs.set(0, vertexID);
		} else {
			vertexIDs.add(vertexID);
		}
	}
	
	@Override
	public void draw(Vertices ver) {
		replaceIDsWithVerticies(ver);
		Vertex vertex = vertices.get(0);
		GL11.glBegin(GL11.GL_POINTS);
		float x = vertex.getxCoordinate().floatValue();
		float y = vertex.getyCoordinate().floatValue();
		float z = vertex.getzCoordinate().floatValue();
		GL11.glVertex3f(x/10,y/10,z/10);
		GL11.glEnd();
	}
	
	private void replaceIDsWithVerticies(Vertices ver) {
		if (vertices.size() == 0) {
			vertices = ver.getMultipleIDs(vertexIDs, Vertices.VERTEX);
		}
	}

	public Integer getVertex() {
		return vertexIDs.get(0);
	}

	@Override
	public int getType() {
		return Element.POINT;
	}

	@Override
	public ArrayList<Integer> getVertexIDs() {
		return vertexIDs;
	}



}
