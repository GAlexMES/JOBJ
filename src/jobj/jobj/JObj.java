package jobj.jobj;

import java.util.ArrayList;

import jobj.faces.FaceGroup;
import jobj.vertex.Verticies;

public class JObj {

	private Verticies verticies;
	private String mtllibFilePaht = "";
	
	ArrayList<FaceGroup> faceGroups;
	
	public JObj(){
		verticies = new Verticies();
		faceGroups = new ArrayList<>();
	}
	
	public void setVerticies(Verticies verticies){
		this.verticies = verticies;
	}
	
	public void addFaceGroup(FaceGroup faceGroup){
		faceGroups.add(faceGroup);
	}
	
	public void setMTLLibFilePath(String filePath){
		this.mtllibFilePaht=filePath;
	}
}
