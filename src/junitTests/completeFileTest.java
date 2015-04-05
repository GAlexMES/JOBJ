package junitTests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import jobj.elements.ElementsGroup;
import jobj.jobj.JObj;
import jobj.parser.Parser;
import jobj.vertex.Vertex;
import jobj.vertex.Vertices;

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
		assertEquals("Wrong vertex" ,x,vertexList.get(4).getxCoordinate());
		assertEquals("Wrong vertex" ,y,vertexList.get(4).getyCoordinate());
		assertEquals("Wrong vertex" ,z,vertexList.get(4).getzCoordinate());
	}
	
	@Test
	public void testObjects(){
		ArrayList<jobj.jobj.Object> objects  = jobj.getObjects();
		String[] objectNames = {"multiplePoints","multipleLines", "multipleFaces","mixedElements"};
		for(int i = 0; i<objects.size();i++){
			assertEquals("Wrong object name" ,objectNames[i],objects.get(i).getName());
		}
	}
	
	@Test
	public void testGroups(){
		jobj.jobj.Object obj = jobj.getObjects().get(3);
		ArrayList<ElementsGroup> elemGroup = obj.getElementsGroup();
		String[] groupNames = {"points", "lines","faces"};
		for(int i = 0 ; i<elemGroup.size();i++){
			assertEquals("Wrong object name" ,groupNames[i],elemGroup.get(i).getName());
			assertEquals("Wrong number of Elements in Group", i+2, elemGroup.get(i).getElementsList().size());
		}
	}
	
	@Test
	public void testPoints(){
		jobj.jobj.Object obj = jobj.getObjects().get(1);
		ArrayList<ElementsGroup> elemGroup = obj.getElementsGroup();
		for(int i = 0; i<elemGroup.size();i++){
			
		}
	}

}
