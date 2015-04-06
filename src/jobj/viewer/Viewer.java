package jobj.viewer;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicReference;

import jobj.datamodel.jobj.JObj;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static org.lwjgl.util.glu.GLU.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * 
 * @author Alexander Brennecke source: http://wiki.lwjgl.org/index.php?title=
 *         Using_a_Resizeable_AWT_Frame_with_LWJGL
 */
public class Viewer extends Frame {
	private static boolean closeRequested = false;
	private final static AtomicReference<Dimension> newCanvasSize = new AtomicReference<Dimension>();
	private JObj jobj;
	private JObjIterator jobjIterator;
	CameraController camera = new CameraController(0, 0, 0);
	float dx = 0.0f;
	float dy = 0.0f;
	float dt = 0.0f;
	float lastTime = 0.0f;
	float time = 0.0f;
	float mouseSensitivity = 0.05f;
	float movementSpeed = 10.0f;

	public Viewer(JObj jobj) {
		this.jobj = jobj;
		jobjIterator = new JObjIterator(jobj);
		init();
	}

	public void rotate(float xAngle, float yAngle) {
		glRotatef(xAngle, 1.0f, 0.0f, 0.0f);
		glRotatef(yAngle, 0.0f, 1.0f, 0.0f);
	}

	private void init() {
		this.setLayout(new BorderLayout());
		final Canvas canvas = new Canvas();
		canvas.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				newCanvasSize.set(canvas.getSize());
			}
		});

		this.addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				canvas.requestFocusInWindow();
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeRequested = true;
			}
		});

		this.add(canvas, BorderLayout.CENTER);

		try {
			Display.setParent(canvas);
			Display.setVSyncEnabled(true);

			this.setPreferredSize(new Dimension(1024, 786));
			this.setMinimumSize(new Dimension(800, 600));
			this.pack();
			this.setVisible(true);

			Display.create();
			Dimension newDim;

			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(45.0f, (float) (WIDTH / HEIGHT), 0.1f, 500f);
			glColor3f(0.5f, 0.7f, 0.8f);
			glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
			glRotatef(-45.0f, 1.0f, 0.0f, 0.0f);
			while (!Display.isCloseRequested() && !closeRequested) {
				glClear(GL_COLOR_BUFFER_BIT);
				
				if(Mouse.isButtonDown(0)){
					updatePosition();
				}
				
				newDim = newCanvasSize.getAndSet(null);

				if (newDim != null) {
					glViewport(0, 0, newDim.width, newDim.height);
				}

				glLoadIdentity();
				camera.lookThrough();
				jobjIterator.render();
				Display.update();
			}

			Display.destroy();
			this.dispose();
			System.exit(0);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	private void updatePosition() {
		time = Sys.getTime();
		dt = (time - lastTime) / 1000.0f;
		lastTime = time;

		dx = Mouse.getDX();
		dy = Mouse.getDY();
		camera.yaw(dx * mouseSensitivity);
		camera.pitch(dy * mouseSensitivity);
	}
}