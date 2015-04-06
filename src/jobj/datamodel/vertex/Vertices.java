package jobj.datamodel.vertex;

import java.util.ArrayList;

/**
 * <h1> Vertices </h1>
 * This class stores all types of vertices in a seperate array List.
 * @author Alexander Brennecke
 *
 */
public class Vertices {

	private ArrayList<Vertex> vertexList;
	private ArrayList<Vertex> textureVertexList;
	private ArrayList<Vertex> normalsVertexList;
	private ArrayList<Vertex> parameterSpaceVertexList;

	public static final int VERTEX = 0;
	public static final int TEXTURE_VERTEX = 1;
	public static final int NORMALS_VERTEX = 2;
	public static final int PARAMETER_SPACE_VERTEX = 3;

	/**
	 * Constructor
	 */
	public Vertices() {
		vertexList = new ArrayList<>();
		textureVertexList = new ArrayList<>();
		normalsVertexList = new ArrayList<>();
		parameterSpaceVertexList = new ArrayList<>();
	}

	/**
	 * <h1>Vertex Request</h1>
	 * 
	 * @param id
	 *            of Vertex
	 * @param vertexType
	 *            should be:
	 *            <ul>
	 *            VERTEX
	 *            </ul>
	 *            <ul>
	 *            TEXTURE_VERTEX
	 *            </ul>
	 *            <ul>
	 *            NORMALS_VERTEX
	 *            </ul>
	 *            <ul>
	 *            PARAMETER_SPACE_VERTEX
	 *            </ul>
	 * @return the requested Vertex. Null if no Vertex was founded.
	 */
	public Vertex getVertex(Integer id, int vertexType) {
		switch (vertexType) {
		case VERTEX:
			return vertexList.get(id - 1);
		case TEXTURE_VERTEX:
			return textureVertexList.get(id - 1);
		case NORMALS_VERTEX:
			return normalsVertexList.get(id - 1);
		case PARAMETER_SPACE_VERTEX:
			return parameterSpaceVertexList.get(id - 1);
		default:
			return null;
		}
	}

	/**
	 * <h1>Vertex Request</h1>
	 * 
	 * @param vertex The Vertex, that should be added
	 * @param vertexType of the given Vertex
	 *            should be:
	 *            <ul>
	 *            VERTEX
	 *            </ul>
	 *            <ul>
	 *            TEXTURE_VERTEX
	 *            </ul>
	 *            <ul>
	 *            NORMALS_VERTEX
	 *            </ul>
	 *            <ul>
	 *            PARAMETER_SPACE_VERTEX
	 *            </ul>
	 */
	public void addVertex(Vertex vertex, int vertexType) {
		switch (vertexType) {
		case VERTEX:
			vertexList.add(vertex);
			break;
		case TEXTURE_VERTEX:
			textureVertexList.add(vertex);
			break;
		case NORMALS_VERTEX:
			normalsVertexList.add(vertex);
			break;
		case PARAMETER_SPACE_VERTEX:
			parameterSpaceVertexList.add(vertex);
			break;
		}
	}
	
	/**
	 * 
	 * @param vertexType Should be one of the final static ints, defined in this class
	 * @return the list for the given type. Null, if the type is invalid.
	 */
	public ArrayList<Vertex> getVertexList(int vertexType){
		switch (vertexType) {
		case VERTEX:
			return vertexList;
		case TEXTURE_VERTEX:
			return textureVertexList;
		case NORMALS_VERTEX:
			return normalsVertexList;
		case PARAMETER_SPACE_VERTEX:
			return parameterSpaceVertexList;
		}
		return null;
	}
	
	/**
	 * This method returns the verticies for the given IDs
	 * @param vertexIDs List of ids
	 * @param type of the Vertices that should be returned. Should be:
	 *            <ul>
	 *            VERTEX
	 *            </ul>
	 *            <ul>
	 *            TEXTURE_VERTEX
	 *            </ul>
	 *            <ul>
	 *            NORMALS_VERTEX
	 *            </ul>
	 *            <ul>
	 *            PARAMETER_SPACE_VERTEX
	 *            </ul>
	 * @return
	 */
	public ArrayList<Vertex> getMultipleIDs (ArrayList<Integer> vertexIDs, int type){
		ArrayList<Vertex> retval = new ArrayList<>();
		for(Integer id : vertexIDs){
			Vertex tempVertex = getVertex(id,type);
			retval.add(tempVertex);
		}
		return retval;
	}
}
