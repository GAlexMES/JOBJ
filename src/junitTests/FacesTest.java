package junitTests;

import static org.junit.Assert.*;
import jobj.faces.Face;

import org.junit.Test;

public class FacesTest {

	String[] rightVerticies = { "f 1 2 3", "f 1//2 2//3 2//4", "f 1/2/ 2/3/ 2/4/", "f 1/2 2/3 2/4",
			"f 1/2/3 2/3/4 2/4/5" };
	String[] falseVerticies = { "f 1/2/3 b c", "f 1/2 2/3/3 2/4/3", "f 1/2/4 2/3/ 2/4/", "f 2/3/1 /3/4 /4/5" };

	@Test
	public void addRightVertex() {
		Face newFace;
		for (int ver = 0; ver < rightVerticies.length; ver++) {
			newFace = new Face();
			String[] line = rightVerticies[ver].split(" ");
			for (int i = 1; i < line.length; i++) {
				try {
					newFace.addVertex(Integer.valueOf(line[i]));
				} catch (NumberFormatException nfe) {
					assertTrue("Wrong vertex", newFace.addVertex(line[i]));
				}
			}

			int vertexSize = newFace.getVertexIDs().size();
			int vertexNormalSize = newFace.getVertexNormalIDs().size();
			int vertexTextureSize = newFace.getVertexTextureIDs().size();
			switch (ver) {
			case 0:
				assertEquals(vertexSize, 3);
				assertEquals(vertexNormalSize, 0);
				assertEquals(vertexTextureSize, 0);
				break;
			case 1:
				assertEquals(vertexSize, 3);
				assertEquals(vertexNormalSize, 3);
				assertEquals(vertexTextureSize, 0);
				break;
			case 2:
				assertEquals(vertexSize, 3);
				assertEquals(vertexNormalSize, 0);
				assertEquals(vertexTextureSize, 3);
				break;
			case 3:
				assertEquals(vertexSize, 3);
				assertEquals(vertexNormalSize, 0);
				assertEquals(vertexTextureSize, 3);
				break;
			case 4:
				assertEquals(vertexSize, 3);
				assertEquals(vertexNormalSize, 3);
				assertEquals(vertexTextureSize, 3);
				break;
			}
		}
	}

	@Test
	public void addFalseVertex() {
		Face newFace;
		for (String vertex : falseVerticies) {
			newFace = new Face();
			String[] line = vertex.split(" ");
			for (int i = 1; i < line.length; i++) {
				try {
					newFace.addVertex(Integer.valueOf(line[i]));
				} catch (NumberFormatException nfe) {
					if (i == 1) {
						assertTrue("Wrong vertex", newFace.addVertex(line[i]));
					} else {
						assertFalse("Wrong vertex", newFace.addVertex(line[i]));
					}
				}
			}
		}
	}
}
