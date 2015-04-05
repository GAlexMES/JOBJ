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
	private FileIterator fileIterator;
	
	/**
	 * Creats a new JObj object.
	 */
	public Parser() {
		jobj = new JObj();
		fileIterator = new FileIterator();
	}

	/**
	 * 
	 * @return the parsed file as a JObj object.
	 */
	public JObj getJobj() {
		jobj = fileIterator.getJObj();
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
		fileIterator.reset();
		parse();
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
				fileIterator.handleLine(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		fileIterator.completed();
	}

	

	
	
	

	
}
