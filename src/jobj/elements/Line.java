package jobj.elements;

public class Line implements Element {

	@Override
	public void addVertex(Integer vertexID) {
		vertexIDs.add(vertexID);
	}

	@Override
	public int getType() {
		return Element.LINE;
	}


}
