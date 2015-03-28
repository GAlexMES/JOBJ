package jobj.jobj;

import java.util.ArrayList;

import jobj.jobj.Object;
import jobj.vertex.Verticies;

public class JObj {

	private Verticies verticies;
	private String mtllibFilePaht = "";
	
	ArrayList<Object> objectList;
	
	public JObj(){
		verticies = new Verticies();
		objectList = new ArrayList<>();
	}
	
	public void setVerticies(Verticies verticies){
		this.verticies = verticies;
	}
	
	public void addObject(Object object){
		objectList.add(object);
	}
	
	public void setMTLLibFilePath(String filePath){
		this.mtllibFilePaht=filePath;
	}
}
