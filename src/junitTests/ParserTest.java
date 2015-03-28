package junitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import jobj.parser.Parser;

import org.junit.Test;

public class ParserTest {

	@Test
	public void readFile() {
		Parser parser = new Parser();
		URL filePath = this.getClass().getResource("/jobj/resources/example/airboat.obj");
		File file = new File(filePath.getPath());
		parser.setFile(file);
	}


}
