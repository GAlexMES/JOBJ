package jobj.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import jobj.jobj.JObj;

public class Parser {

	private JObj jobj;
	private File file;

	public JObj getJobj() {
		return jobj;
	}

	public void setFile(File file) {
		this.file = file;
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
	}

	private void handleLine(String line) {
		System.out.println(line);
	}
}
