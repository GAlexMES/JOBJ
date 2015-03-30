package jobj.elements;

public class Point implements Element{

	@Override
	public void addVertex(Integer vertexID) {
		if(vertexIDs.size()>0){
		vertexIDs.set(0, vertexID);
		}
		else{
			vertexIDs.add(vertexID);
		}
	}
	
	public Integer getVertex(){
		return vertexIDs.get(0);
	}

	@Override
	public int getType() {
		return Element.POINT;
	}

}
