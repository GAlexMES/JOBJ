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
import jobj.vertex.Verticies;

/**
 * <h1>Main Parsing Class</h1>
 * 
 * @author Alexander Brennecke
 *
 */
public class Parser {

	private JObj jobj;
	private File file;
	private Verticies tempVerticies;
	private ElementsGroup tempElementsGroup;
	private Comments tempComments;
	private Object tempObject;
	private int tempSmoothinGroup;
	// [v;vt;vn;vp]
	private Integer[] vertexCounter = { 0, 0, 0, 0 };

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
		tempVerticies = new Verticies();
		tempComments = new Comments();
		tempObject = new Object();
		tempSmoothinGroup = 0;
		resetVertexCounter();
		parse();
	}

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
		jobj.setVerticies(tempVerticies);
		tempObject.addFaceGroup(tempElementsGroup);
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
			newVertex(splittedLine, Verticies.VERTEX);
			break;
		case "vt":
			newVertex(splittedLine, Verticies.TEXTURE_VERTEX);
			break;
		case "vn":
			newVertex(splittedLine, Verticies.NORMALS_VERTEX);
			break;
		case "vp":
			newVertex(splittedLine, Verticies.PARAMETER_SPACE_VERTEX);
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
		int vertex = Integer.valueOf(line[1]);
		point.addVertex(vertex);
		tempElementsGroup.addElement(point);
	}

	private void newLine(String[] line) {
		Line lineElem = new Line();
		if (line.length > 1) {
			for (int i = 1; i < line.length; i++) {
				try {
					lineElem.addVertex(Integer.valueOf(line[i]));
				} catch (NumberFormatException nfe) {
					System.out.println("Parsing Error: Wrong parameter for line");
				}
			}
		} else {
			System.out.println("Parsing Error: Missing parameters for line");
		}
		tempElementsGroup.addElement(lineElem);
	}

	private void newObject(String[] line) {
		jobj.addObject(tempObject);
		tempObject = new Object();

		String objectName = "";
		if (line.length > 1) {
			for (int i = 1; i < line.length; i++) {
				objectName = objectName + " " + line[i];
			}
		} else {
			objectName = "Unnamed";
		}
		tempObject.setName(objectName);
	}

	private void newMTLUse(String[] line) {
		if (line.length > 1) {
			tempElementsGroup.setMTLLibTexture(line[1]);
		} else {
			System.err.println("Parsing Error: No texture given!");
		}
	}

	private void newComment(String line) {
		String[] comment = line.split("#");
		if (comment.length > 1) {
			Comment tempComment = new Comment(comment[1]);
			tempComments.addComment(tempComment);
		} else {
			System.err.println("Parsing Error: No comment given!");
		}
	}

	private void mitllibImport(String[] splittedLine) {
		jobj.setMTLLibFilePath(splittedLine[1]);
	}

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

	private void newGroup(String[] line) {
		if (tempElementsGroup != null) {
			if (tempElementsGroup.getElementsList().size() > 0) {
				tempObject.addFaceGroup(tempElementsGroup);
			}
		}

		String groupName;
		if (line.length > 1) {
			if (line[1].equals("")) {
				groupName = "Unknown Group Name";
			} else {
				groupName = line[1];
			}
		} else {
			groupName = "Unknown Group Name";
		}
		tempElementsGroup = new ElementsGroup(groupName);
	}

	private void newFace(String[] line) {
		Face newFace = new Face();
		for (int i = 1; i < line.length; i++) {
			try {
				int vertexID = Integer.valueOf(line[i]);
				if (vertexID < 0) {
					vertexID = vertexCounter[Verticies.VERTEX] + (vertexID + 1);
				}
				newFace.addVertex(vertexID);
			} catch (NumberFormatException nfe) {
				newFace.addVertex(line[i], vertexCounter);
			}
		}
		newFace.setSmoothingGroupe(tempSmoothinGroup);
		tempElementsGroup.addElement(newFace);
	}

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
		tempVerticies.addVertex(newVertex, vertexType);
		vertexCounter[vertexType] = vertexCounter[vertexType]+1;
	}
}
