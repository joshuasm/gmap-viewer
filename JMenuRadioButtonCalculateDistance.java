import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.beans.*; //Property change stuff
import javax.swing.table.*;
import com.sun.image.codec.jpeg.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.geom.*;

/**
 * It is the radio button action when the user wants to calculate distance.
 */
public class JMenuRadioButtonCalculateDistance extends JMenuRadioButtonAction {
	
	
	/**
	 * 
	 */
	static final long serialVersionUID = 42643951;
	
	/**
	 * Constructor for JMenuRadioButtonCalculateDistance class
	 * @param registeredObject Sets a reference to the GUI object
	 */
	public JMenuRadioButtonCalculateDistance(GUI registeredObject) {
		super("Calculate Distance",registeredObject);
	}
	
	/**
	 * Sets the mode of the GUI to Distance Mode
	 */
	public void run() {
		GUI gui = (GUI) registeredObject;
		GPane pane = gui.getTopPane();
		pane.setMode(GPane.DISTANCE_MODE);
		super.setSelected(true);
		gui.getNotifier().firePaneEvent(this);
		gui.getProgressMeter().getPanel().repaint();
	}
	
	/**
	 * 
	 * @param object
	 */
	public void paneEvent(Object object){
		GUI gui = (GUI)registeredObject;
		GPane pane = gui.getTopPane();
		if(pane == null) return;
		super.setSelected(pane.getMode() == GPane.DISTANCE_MODE);
	}
}
