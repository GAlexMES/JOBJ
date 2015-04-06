package junitTests;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import jobj.datamodel.jobj.JObj;
import jobj.parser.Parser;
import jobj.viewer.Viewer;

import org.junit.Before;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class CanvasTest {

	private JObj jobj;

	@Before
	public void parseFile() {
		Parser p = new Parser();
		URL filePath = this.getClass().getResource("/resources/airboat.obj");
		File file = new File(filePath.getPath());
		p.setFile(file);
		this.jobj = p.getJobj();
	}

	@Test
	public void startCanvas() {
		Viewer viewer = new Viewer(jobj);

	}
}
