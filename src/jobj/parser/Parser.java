package jobj.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import jobj.comments.Comment;
import jobj.comments.Comments;
import jobj.faces.Face;
import jobj.faces.FaceGroup;
import jobj.jobj.JObj;
import jobj.vertex.Vertex;
import jobj.vertex.Verticies;

public class Parser {

	private JObj jobj;
	private File file;
	private Verticies tempVerticies;
	private FaceGroup tempFaceGroup;
	private Comments tempComments;
	private int tempSmoothinGroup;

	public Parser() {
		jobj = new JObj();
	}

	public JObj getJobj() {
		return jobj;
	}

	public void setFile(File file) {
		this.file = file;
		jobj = new JObj();
		tempVerticies = new Verticies();
		tempComments = new Comments();
		tempSmoothinGroup = 0;
		parse();
	}

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
		jobj.addFaceGroup(tempFaceGroup);
	}

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
		case "f":
			newFace(splittedLine);
			break;
		case "g":
			newGroup(splittedLine);
			break;
		case "s":
			newSmoothingGroup(splittedLine);
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

	private void newMTLUse(String[] line) {
		if (line.length > 1) {
			tempFaceGroup.setMTLLibTexture(line[1]);
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
		if (tempFaceGroup != null) {
			if (tempFaceGroup.getFaceList().size() > 0) {
				jobj.addFaceGroup(tempFaceGroup);
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
		tempFaceGroup = new FaceGroup(groupName);
	}

	private void newFace(String[] line) {
		Face newFace = new Face();
		for (int i = 1; i < line.length; i++) {
			try{
				newFace.addVertex(Integer.valueOf(line[i]));
			}
			catch(NumberFormatException nfe){
				newFace.addVertex(line[i]);
			}
		}
		newFace.setSmoothingGroupe(tempSmoothinGroup);
		tempFaceGroup.addFace(newFace);
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
	}
}
