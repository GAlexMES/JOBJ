package jobj.jobj;

import java.util.ArrayList;

import jobj.vertex.Vertices;

/**
 * <h1> JObj </h1>
 * This is the most important class. After the parsing of a .obj file is finished, al information will be saved in an object of this class.
 * To see, which information will be parsed check out our <a href="https://github.com/haloshat/JOBJ/blob/master/README.md"> READNE.md at our git repository </a>
 * @author Alexander Brennecke
 *
 */
public class JObj {

	/**
	 * Initialization
	 */
	private Vertices vertices;
	private String mtllibFilePaht = "";
	
	ArrayList<Object> objectList;
	
	/**
	 * Construcotr
	 */
	public JObj(){
		vertices = new Vertices();
		objectList = new ArrayList<>();
	}
	
	public void setVertices(Vertices verticies){
		this.vertices = verticies;
	}
	
	public void addObject(Object object){
		objectList.add(object);
	}
	
	public void setMTLLibFilePath(String filePath){
		this.mtllibFilePaht=filePath;
	}
	
	public Vertices getVerticies(){
		return this.vertices;
	}
	
	public ArrayList<Object> getObjects(){
		return this.objectList;
	}
}
