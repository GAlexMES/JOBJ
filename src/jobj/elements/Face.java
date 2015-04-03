package jobj.elements;

import java.util.ArrayList;


/**
 * <h1> Face </h1>
 * This class is for the "f" Face tag in .obj files.
 * It contains three ArrayLists, which stores the position of the used verticies.
 * Also it could contains a smoothing group.
 * @author Alexander Brennecke
 *
 */
public class Face implements Element{
	/**
	 * Initialization
	 */
	private int smoothingGroup;
	private ArrayList<Integer> vertexIDs;
	private ArrayList<Integer> vertexNormalIDs;
	private ArrayList<Integer> vertexTextureIDs;
	
	/**
	 * Constructor
	 */
	public Face() {
		smoothingGroup = 0;
		vertexIDs = new ArrayList<>();
		vertexNormalIDs = new ArrayList<>();
		vertexTextureIDs= new ArrayList<>();
		
	}

	/**
	 * Is used, when a v/vn/vt is used in the .obj file 
	 * @param vertexID The string in the v/vn/vt pattern
	 * @param vertexCounter the counter for verticies to handle negativ verticies
	 * @return true if everything works and false if something went wrong
	 */
	public boolean addVertex(String vertexID, Integer[] vertexCounter){
		if(vertexID.contains("/")){
			String[] ids = vertexID.split("/");
			for(int i = 0; i<ids.length;i++){
				try{
					Integer id = Integer.valueOf(ids[i]);
					if(id<0){
						id = vertexCounter[i] + (id + 1);
					}
					switch(i){
					case 0: vertexIDs.add(id); break;
					case 1: vertexTextureIDs.add(id); break;
					case 2: vertexNormalIDs.add(id); break;
					}
				}
				catch(NumberFormatException nfe){
				}
			}
		}
		else{
			return false;
		}
		return checkLists();
	}

	/**
	 * Checks if the arrayLists have a correct length.
	 * A face needs for each vertex the same pattern.
	 * @return true if everything works and false if something went wrong
	 */
	private boolean checkLists() {
		int vSize = vertexIDs.size();
		int vnSize = vertexNormalIDs.size();
		int vtSize = vertexTextureIDs.size();

		if (vSize != vnSize && vnSize != 0) {
			return false;
		}
		if (vSize != vtSize && vtSize != 0) {
			return false;
		}
		return true;
	}

	/**
	 * Is used, when the vn/vt is not given.
	 */
	public void addVertex(Integer id) {
		vertexIDs.add(id);
	}	

	public ArrayList<Integer> getVertexNormalIDs() {
		return vertexNormalIDs;
	}

	public ArrayList<Integer> getVertexTextureIDs() {
		return vertexTextureIDs;
	}

	public void setSmoothingGroupe(int smoothingGroup) {
		this.smoothingGroup = smoothingGroup;
	}
	
	
	@Override
	public ArrayList<Integer> getVertexIDs() {
		return vertexIDs;
	}
	@Override
	public int getType() {
		return Element.FACE;
	}

	
}
