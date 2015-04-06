package junitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import jobj.datamodel.elements.Element;
import jobj.datamodel.elements.ElementsGroup;
import jobj.datamodel.elements.Face;
import jobj.datamodel.jobj.JObj;
import jobj.datamodel.vertex.Vertex;
import jobj.datamodel.vertex.Vertices;
import jobj.parser.Parser;
import jobj.viewer.JObjIterator;
import jobj.viewer.Viewer;

import org.junit.Before;
import org.junit.Test;

public class completeFileTest {

	private JObj jobj;

	@Before
	public void parseFile() {
		Parser p = new Parser();
		URL filePath = this.getClass().getResource("/resources/unitExample.obj");
		File file = new File(filePath.getPath());
		p.setFile(file);
		this.jobj = p.getJobj();
	}

	@Test
	public void testVerticies() {
		Vertices vertices = jobj.getVerticies();
		ArrayList<Vertex> vertexList = null;
		for (int vertexType = 0; vertexType < 4; vertexType++) {
			vertexList = vertices.getVertexList(vertexType);
			assertEquals("Something faild with the nr." + vertexType + "Vertex parsing", vertexList.size(), 5);
		}
		Double x = -0.5;
		Double y = 0.5;
		Double z = 1.25;
		assertEquals("Wrong vertex", x, vertexList.get(4).getxCoordinate());
		assertEquals("Wrong vertex", y, vertexList.get(4).getyCoordinate());
		assertEquals("Wrong vertex", z, vertexList.get(4).getzCoordinate());
	}

	@Test
	public void testObjects() {
		ArrayList<jobj.datamodel.elements.Object> objects = jobj.getObjects();
		String[] objectNames = { "multiplePoints", "multipleLines", "multipleFaces", "mixedElements" };
		for (int i = 0; i < objects.size(); i++) {
			assertEquals("Wrong object name", objectNames[i], objects.get(i).getName());
		}
	}

	@Test
	public void testGroups() {
		jobj.datamodel.elements.Object obj = jobj.getObjects().get(3);
		ArrayList<ElementsGroup> elemGroup = obj.getElementsGroup();
		String[] groupNames = { "points", "lines", "faces" };
		for (int i = 0; i < elemGroup.size(); i++) {
			assertEquals("Wrong object name", groupNames[i], elemGroup.get(i).getName());
			assertEquals("Wrong number of Elements in Group", i + 2, elemGroup.get(i).getElementsList().size());
		}
	}

	@Test
	public void testPoints() {
		jobj.datamodel.elements.Object obj = jobj.getObjects().get(0);
		ElementsGroup elemGroup = obj.getElementsGroup().get(0);
		ArrayList<Element> elemList = elemGroup.getElementsList();
		Integer[] values = { 1, 2, 5 };
		for (int i = 0; i < elemList.size(); i++) {
			assertEquals("Wrong Type!", Element.POINT, elemList.get(i).getType());
			assertEquals("Wrong value in Point!", values[i], elemList.get(i).getVertexIDs().get(0));
			assertEquals("Too many vertices for points", 1, elemList.get(i).getVertexIDs().size());
		}
	}

	@Test
	public void testLines() {
		jobj.datamodel.elements.Object obj = jobj.getObjects().get(1);
		ElementsGroup elemGroup = obj.getElementsGroup().get(0);
		ArrayList<Element> elemList = elemGroup.getElementsList();
		Integer[] line1 = { 1, 2 };
		Integer[] line2 = { 2, 3 };
		Integer[] line3 = { 1, 2, 3, 4, 5 };
		Integer[][] lines = { line1, line2, line3 };
		for (int i = 0; i < elemList.size(); i++) {
			assertEquals("Wrong Type!", Element.LINE, elemList.get(i).getType());
			assertTrue("Not enough vertices for lines", elemList.get(i).getVertexIDs().size() > 1);
			for (int v = 0; v < elemList.get(i).getVertexIDs().size(); v++) {
				assertEquals("Wrong value in Line!", lines[i][v], elemList.get(i).getVertexIDs().get(v));
			}
		}
	}

	@Test
	public void testFaces() {
		jobj.datamodel.elements.Object obj = jobj.getObjects().get(2);
		ElementsGroup elemGroup = obj.getElementsGroup().get(0);
		ArrayList<Element> elemList = elemGroup.getElementsList();
		Integer[] line1 = { 1, 2 };
		Integer[] line2 = { 2, 3 };
		Integer[] line3 = { 1, 2, 3, 4, 5 };
		Integer[][] lines = { line1, line2, line3 };
		for (int i = 0; i < elemList.size(); i++) {
			assertEquals("Wrong Type!", Element.FACE, elemList.get(i).getType());
			assertTrue("Not enough vertices for Face", elemList.get(i).getVertexIDs().size() > 2);
			switch (i) {
			case 0:
				Integer[] case0Values = { 1, 2, 3 };
				checkFaceVertices(case0Values,((Face) elemList.get(i)).getVertexIDs());
				assertEquals("Wrong smoothing Group in Face!", 0, ((Face) elemList.get(i)).getSmoothingGroup());
				break;
			case 1:
				Integer[] case1Values = { 1, 2, 3, 2 };
				checkFaceVertices(case1Values,((Face) elemList.get(i)).getVertexIDs());
				assertEquals("Wrong smoothing Group in Face!", 1, ((Face) elemList.get(i)).getSmoothingGroup());
				break;
			case 2:
				Integer[] case2Values = { 1, 2, 3 };
				checkFaceVertices(case2Values, ((Face) elemList.get(i)).getVertexIDs());
				checkFaceVertices(case2Values, ((Face) elemList.get(i)).getVertexNormalIDs());
				assertEquals("Wrong smoothing Group in Face!", 2, ((Face) elemList.get(i)).getSmoothingGroup());
				break;
			case 3:
				Integer[] case3Values = { 1, 2, 3 };
				checkFaceVertices(case3Values,  ((Face) elemList.get(i)).getVertexIDs());
				Integer[] case3TexValues = { 3, 4, 5 };
				checkFaceVertices(case3TexValues,  ((Face) elemList.get(i)).getVertexNormalIDs());
				Integer[] case3NorValues = { 2, 3, 4 };
				checkFaceVertices(case3NorValues,  ((Face) elemList.get(i)).getVertexTextureIDs());
				assertEquals("Wrong smoothing Group in Face!", 3, ((Face) elemList.get(i)).getSmoothingGroup());
				break;
			case 4:
				Integer[] case4Values = { 1, 2, 3 };
				checkFaceVertices(case4Values, ((Face) elemList.get(i)).getVertexIDs());
				Integer[] case4TexValues = { 2, 3, 4 };
				checkFaceVertices(case4TexValues, ((Face) elemList.get(i)).getVertexTextureIDs());
				assertEquals("Wrong smoothing Group in Face!", 4, ((Face) elemList.get(i)).getSmoothingGroup());
				break;
			case 5:
				Integer[] case5Values = { 1, 2, 3 };
				checkFaceVertices(case5Values,  ((Face) elemList.get(i)).getVertexIDs());
				Integer[] case5TexValues = { 3, 4, 5 };
				checkFaceVertices(case5TexValues,  ((Face) elemList.get(i)).getVertexNormalIDs());
				Integer[] case5NorValues = { 2, 3, 2 };
				checkFaceVertices(case5NorValues,  ((Face) elemList.get(i)).getVertexTextureIDs());
				assertEquals("Wrong smoothing Group in Face!", 5, ((Face) elemList.get(i)).getSmoothingGroup());
				break;
			case 6:
				Integer[] case6Values = { 1, 4, 3 };
				checkFaceVertices(case6Values,  ((Face) elemList.get(i)).getVertexIDs());
				Integer[] case6TexValues = { 2, 3, 4 };
				checkFaceVertices(case6TexValues,  ((Face) elemList.get(i)).getVertexTextureIDs());
				assertEquals("Wrong smoothing Group in Face!", 0, ((Face) elemList.get(i)).getSmoothingGroup());
				break;
			}
			

		}
	}
	
	@Test
	public void checkObjectIterator(){
		JObjIterator jobjIter = new JObjIterator(jobj);
		jobjIter.generateView();
		assertTrue("Something doen't work in the ObjIterator",true);
	}

	private void checkFaceVertices(Integer[] exceptedValues, ArrayList<Integer> realValues) {
		for (int i = 0; i < exceptedValues.length; i++) {
			assertEquals("Wrong value in Face!", exceptedValues[i], realValues.get(i));
		}
	}
}
