package jobj.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import jobj.faces.Face;
import jobj.faces.FaceGroup;
import jobj.jobj.JObj;
import jobj.vertex.Vertex;
import jobj.vertex.Verticies;

public class Parser {

	private JObj jobj;
	private File file;
	private Verticies verticies;
	private FaceGroup tempFaceGroup;

	public Parser() {
		jobj = new JObj();
	}

	public JObj getJobj() {
		return jobj;
	}

	public void setFile(File file) {
		this.file = file;
		jobj = new JObj();
		verticies = new Verticies();
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
		jobj.setVerticies(verticies);
		jobj.addFaceGroup(tempFaceGroup);
	}

	private void handleLine(String line) {
		if (line.startsWith("v")) {
			newVertex(line);
		} else if (line.startsWith("f")) {
			newFace(line);
		} else if (line.startsWith("g")) {
			newGroupe(line);
		}
	}

	private void newGroupe(String line) {
		if (tempFaceGroup != null) {
			if (tempFaceGroup.getFaceList().size() > 0) {
				jobj.addFaceGroup(tempFaceGroup);
			}
		}
		tempFaceGroup = new FaceGroup();

	}

	private void newFace(String line) {
		String[] splittedLine = line.split(" ");
		Face newFace = new Face();
		for (int i = 1; i < splittedLine.length; i++) {
			newFace.addVertex(Integer.valueOf(splittedLine[i]));
		}
		tempFaceGroup.addFace(newFace);
	}

	private void newVertex(String line) {
		String[] splittedLine = line.split(" ");
		Double xCoordinate = Double.valueOf(splittedLine[1]);
		Double yCoordinate = Double.valueOf(splittedLine[2]);
		Double zCoordinate = Double.valueOf(splittedLine[3]);
		Vertex newVertex = new Vertex(xCoordinate, yCoordinate, zCoordinate);
		verticies.addVertex(newVertex);
	}
}
