package jobj.datamodel.elements;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import jobj.datamodel.vertex.Vertex;
import jobj.datamodel.vertex.Vertices;

/**
 * <h1> Line </h1>
 * This class handles the "l" Line tag in .obj files.
 * It saves a number of verticies.
 * @author Alexander Brennecke
 *
 */
public class Line implements Element {

	private ArrayList<Integer> vertexIDs;
	private ArrayList<Vertex> vertices;
	
	public Line(){
		vertexIDs = new ArrayList<>();
	}
	
	@Override
	public void addVertex(Integer vertexID) {
		vertexIDs.add(vertexID);
	}

	@Override
	public int getType() {
		return Element.LINE;
	}

	@Override
	public ArrayList<Integer> getVertexIDs() {
		return vertexIDs;
	}

	private void replaceIDsWithVerticies(Vertices ver) {
		if (vertices.size() == 0) {
			vertices = ver.getMultipleIDs(vertexIDs, Vertices.VERTEX);
		}
	}
	
	@Override
	public void draw(Vertices ver) {
		replaceIDsWithVerticies(ver);
		GL11.glBegin(GL11.GL_LINE_STRIP);

		for(Vertex vertex : vertices){
			float x = vertex.getxCoordinate().floatValue();
			float y = vertex.getyCoordinate().floatValue();
			float z = vertex.getzCoordinate().floatValue();
			GL11.glVertex2d(x/10,y/10);
		}
		
		GL11.glEnd();
	}


}
