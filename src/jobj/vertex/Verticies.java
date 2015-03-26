package jobj.vertex;

import java.util.ArrayList;

public class Verticies {

	private ArrayList<Vertex> vertexList;
	
	public Verticies(){
		vertexList = new ArrayList<>();
	}
	
	public Vertex getVertex(Integer id){
		return vertexList.get(id+1);
	}
	
	public void addVertex(Vertex vertex){
		vertexList.add(vertex);
	}
}
