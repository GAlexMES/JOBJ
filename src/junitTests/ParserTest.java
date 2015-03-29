package junitTests;

import java.io.File;
import java.net.URL;

import jobj.parser.Parser;

import org.junit.Test;

public class ParserTest {

	private String[] files = { "airboat", "capsule" };

	@Test
	public void readFile() {
		Parser parser = new Parser();
		for (String fileName : files) {
			URL filePath = this.getClass().getResource("/resources/"+fileName+".obj");
			File file = new File(filePath.getPath());
			parser.setFile(file);
		}
	}

}
