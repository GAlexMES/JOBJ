package jobj.parser;

import java.io.File;

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
	
	private void parse(){
		//DO SOMETHING!
	}
}
