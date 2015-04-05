package jobj.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import jobj.comments.Comment;
import jobj.comments.Comments;
import jobj.elements.*;
import jobj.jobj.JObj;
import jobj.jobj.Object;
import jobj.vertex.Vertex;
import jobj.vertex.Vertices;

/**
 * <h1>Main Parsing Class</h1>
 * 
 * This class iterates over the given .obj file and will save it line for line
 * to a complete JObj file.
 * 
 * @author Alexander Brennecke
 *
 */
public class Parser {

	/**
	 * Initialization
	 */
	private JObj jobj;
	private File file;
	private Vertices tempVertices;
	private ElementsGroup tempElementsGroup;
	private Comments tempComments;
	private Object tempObject;
	private int tempSmoothinGroup;
	// [v;vt;vn;vp]
	private Integer[] vertexCounter = { 0, 0, 0, 0 };

	private final String unknownGroupName = "Unknown Group Name";

	/**
	 * Creats a new JObj object.
	 */
	public Parser() {
		jobj = new JObj();
	}

	/**
	 * 
	 * @return the parsed file as a JObj object.
	 */
	public JObj getJobj() {
		return jobj;
	}

	/**
	 * Parses the given file.
	 * 
	 * @param file
	 *            The file, that should be parsed.
	 */
	public void setFile(File file) {
		this.file = file;
		jobj = new JObj();
		tempVertices = new Vertices();
		tempComments = new Comments();
		tempObject = new Object();
		tempElementsGroup = new ElementsGroup(unknownGroupName);
		tempSmoothinGroup = 0;
		resetVertexCounter();
		parse();
	}

	/**
	 * This method resets the vertexCounter.
	 */
	private void resetVertexCounter() {
		for (int i = 0; i < vertexCounter.length; i++) {
			vertexCounter[0] = 0;
		}
	}

	/**
	 * Parses the current file line by line.
	 */
	private void parse() {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			fr = new FileReader(file);
			String line = "";
			while ((line = br.readLine()) != null) {
				handleLine(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		jobj.setVertices(tempVertices);
		tempObject.addElementsGroup(tempElementsGroup);
		jobj.addObject(tempObject);
	}

	/**
	 * Handles the given line by the tag, which stands bevor the first space.
	 * 
	 * @param line
	 *            that should be parsed
	 */
	private void handleLine(String line) {
		String[] splittedLine = line.split(" ");
		String elementTag = splittedLine[0];

		switch (elementTag) {

		case "v":
			newVertex(splittedLine, Vertices.VERTEX);
			break;
		case "vt":
			newVertex(splittedLine, Vertices.TEXTURE_VERTEX);
			break;
		case "vn":
			newVertex(splittedLine, Vertices.NORMALS_VERTEX);
			break;
		case "vp":
			newVertex(splittedLine, Vertices.PARAMETER_SPACE_VERTEX);
			break;
		case "l":
			newLine(splittedLine);
			break;
		case "p":
			newPoint(splittedLine);
			break;
		case "f":
			newFace(splittedLine);
			break;
		case "g":
			newGroup(splittedLine);
			break;
		case "s":
			newSmoothingGroup(splittedLine);
			break;
		case "o":
			newObject(splittedLine);
			break;
		case "#":
			newComment(line);
			break;
		case "mtllib":
			mitllibImport(splittedLine);
			break;
		case "usemtl":
			newMTLUse(splittedLine);
			break;
		case " ":
			break;
		case "":
			break;
		default:
			System.err.println("Parsing Error: Unknown Element tag: " + elementTag);
			break;
		}
	}

	private void newPoint(String[] line) {
		Point point = new Point();
		int vertexID = Integer.valueOf(line[1]);
		vertexID = convertRelativToAbsolutVertexID(vertexID);
		point.addVertex(vertexID);
		tempElementsGroup.addElement(point);
	}

	private void newLine(String[] line) {
		Line lineElem = new Line();
		if (line.length > 1) {
			for (int i = 1; i < line.length; i++) {
				try {
					int vertexID = Integer.valueOf(line[i]);
					vertexID = convertRelativToAbsolutVertexID(vertexID);
					lineElem.addVertex(vertexID);
				} catch (NumberFormatException nfe) {
					System.out.println("Parsing Error: Wrong parameter for line");
				}
			}
		} else {
			System.out.println("Parsing Error: Missing parameters for line");
		}
		tempElementsGroup.addElement(lineElem);
	}

	/**
	 * This method creates a new Object object and saves the old one to the JObj
	 * object.
	 * 
	 * @param line
	 *            The line, which includes the "o" tag.
	 */
	private void newObject(String[] line) {
		if (tempElementsGroup.getElementsList().size()>0) {
			tempObject.addElementsGroup(tempElementsGroup);
		}
		tempElementsGroup = new ElementsGroup(unknownGroupName);
		
		if (tempObject.getName()!=null || tempObject.getElementsGroup().size()>0) {
			jobj.addObject(tempObject);
		}
		tempObject = new Object();

		String objectName = "";
		if (line.length > 1) {
			for (int i = 1; i < line.length; i++) {
				if(i==1){
					objectName = line[i];
				}
				else{
					objectName = objectName + " " + line[i];
				}
			}
		} else {
			objectName = "Unnamed";
		}
		tempObject.setName(objectName);
	}

	/**
	 * This method adds a mtlTexture to a ElementGroup.
	 * 
	 * @param line
	 *            The line, which includes the "usemtl" tag.
	 */
	private void newMTLUse(String[] line) {
		if (line.length > 1) {
			tempElementsGroup.setMTLLibTexture(line[1]);
		} else {
			System.err.println("Parsing Error: No texture given!");
		}
	}

	/**
	 * This method adds a new Comment, which includes the text behind the "#"
	 * tag to the Comments object.
	 * 
	 * @param line
	 *            The line, which includes the "#" tag.
	 */
	private void newComment(String line) {
		String[] comment = line.split("#");
		if (comment.length > 1) {
			Comment tempComment = new Comment(comment[1]);
			tempComments.addComment(tempComment);
		} else {
			System.err.println("Parsing Error: No comment given!");
		}
	}

	/**
	 * This method sets the path to the .mtl file, which will be used for the
	 * file.
	 * 
	 * @param splittedLine
	 *            The line, which includes the "mtllib" tag.
	 */
	private void mitllibImport(String[] splittedLine) {
		jobj.setMTLLibFilePath(splittedLine[1]);
	}

	/**
	 * This method saves the smoothing group to a local variable. Every
	 * following face will use it.
	 * 
	 * @param line
	 *            The line, which includes the "s" tag.
	 */
	private void newSmoothingGroup(String[] line) {
		try {
			tempSmoothinGroup = Integer.valueOf(line[1]);
		} catch (NumberFormatException nfe) {
			if (line[1].equals("off")) {
				tempSmoothinGroup = 0;
			} else {
				System.err.println("Parsing Error: Unknown Smoothing Group ('s'): " + line[1]);
			}
		}
	}

	/**
	 * This method defines a new Element Group. The old group will be added to
	 * the temporary Object object.
	 * 
	 * @param line
	 *            The line, which includes the "g" tag.
	 */
	private void newGroup(String[] line) {
		if (tempElementsGroup != null) {
			if (tempElementsGroup.getElementsList().size() > 0) {
				tempObject.addElementsGroup(tempElementsGroup);
			}
		}

		String groupName;
		if (line.length > 1) {
			if (line[1].equals("")) {
				groupName = unknownGroupName;
			} else {
				groupName = line[1];
			}
		} else {
			groupName = unknownGroupName;
		}
		tempElementsGroup = new ElementsGroup(groupName);
	}

	/**
	 * This method defines a new Face object, which will be added to the
	 * temporary group object.
	 * 
	 * @param line
	 *            The line, which includes the "f" tag.
	 */
	private void newFace(String[] line) {
		Face newFace = new Face();
		for (int i = 1; i < line.length; i++) {
			try {
				int vertexID = Integer.valueOf(line[i]);
				vertexID = convertRelativToAbsolutVertexID(vertexID);
				newFace.addVertex(vertexID);
			} catch (NumberFormatException nfe) {
				newFace.addVertex(line[i], vertexCounter);
			}
		}
		newFace.setSmoothingGroup(tempSmoothinGroup);
		tempElementsGroup.addElement(newFace);
	}
	
	/**
	 * Converts a negative/relativ vertex ID to a absolut one.
	 * @param vertexID the vertex ID, thath should be converted
	 * @return the converted vertex ID
	 */
	private int convertRelativToAbsolutVertexID(int vertexID){
		if (vertexID < 0) {
			vertexID = vertexCounter[Vertices.VERTEX] + (vertexID + 1);
		}
		return vertexID;
	}

	/**
	 * This method creates a new vertex, which will be added to a ArrayList in
	 * the Vertices object.
	 * 
	 * @param line
	 *            The line, which includes the "v" / "vn" / "vp" / "vt" tag.
	 * @param vertexType
	 *            the type of the vertex. Should be one of the static types,
	 *            defined in the Vertices class.
	 */
	private void newVertex(String[] line, int vertexType) {
		int lineLength = line.length;
		Double zCoordinate = null;
		Double yCoordinate = null;
		Double xCoordinate = null;
		switch (lineLength) {
		case 4:
			zCoordinate = Double.valueOf(line[3]);
		case 3:
			yCoordinate = Double.valueOf(line[2]);
		case 2:
			xCoordinate = Double.valueOf(line[1]);
		}
		Vertex newVertex = new Vertex(xCoordinate, yCoordinate, zCoordinate);
		tempVertices.addVertex(newVertex, vertexType);
		vertexCounter[vertexType] = vertexCounter[vertexType] + 1;
	}
}
