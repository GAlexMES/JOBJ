package jobj.elements;

import java.util.ArrayList;

public interface Element {

	ArrayList<Integer> vertexIDs = new ArrayList<>();
	ArrayList<Integer> vertexNormalIDs = new ArrayList<>();
	ArrayList<Integer> vertexTextureIDs= new ArrayList<>();
	
	void addVertex(Integer vertexID);
}
