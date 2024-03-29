import javax.swing.*;
import java.awt.*;
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
 * Class EmbeddedProgressMeter operates as a controller class for ProgressMeter. It
 * performs updates for the current position and status of the bar, painting these changes
 * to the panel when necessary. It extends ProgressMeter and implements MouseListener.
 */
public class EmbeddedProgressMeter extends ProgressMeter implements MouseListener{
   /** Declaration of the panel instance for the progress bar */
   private JPanel panel;
   /** Declaration of the actual progress bar instance */
   private JProgressBar progressBar;
   /** Declaration of a button for canceling any action using the progress bar */
   private JButton cancel;
   /** Declaration of message displaying the status of the progress bar */
   private JLabel messageLabel;
   /** Declaration of a thread instance used by the progress bar */
   private Thread thread;
   /** Declaration of default string message for when the progress bar is not in use */
   private static final String notWorkingMessage = "Done.";

   /** Creates a new instance of EmbeddedProgressMeter */
   public EmbeddedProgressMeter() {
      super(null,0.0,notWorkingMessage);

      //initialize components
      progressBar = new JProgressBar();
      progressBar.setMinimum(0);
      progressBar.setMaximum(100);
      progressBar.setStringPainted(false);
      progressBar.setValue(0);

      cancel = new JButton("Cancel");
      cancel.addMouseListener(this);
      cancel.setPreferredSize(new Dimension(90,18));

      messageLabel = new JLabel();
      messageLabel.setPreferredSize(new Dimension(200,22));

      panel = new JPanel();
      panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 1));
      panel.add(progressBar);
      panel.add(cancel);
      panel.add(messageLabel);

      update();
   }

   /** 
    * Method to set the message for the current status of the progress bar
    *
    * @param message The message to display next to the progress bar
    * @param o       The object of the progress bar
    * @return        The boolean result for whether the message was set or not 
    */
   public boolean setMessage( String message, Object o ) {
      if( super.setMessage( message, o ) == true ) {
         update();
         return true;
      }
      else {
      return false;
      }
   }
   
   /** 
    * Method to set the percentage for the progress bar
    *
    * @param percent The percentage to be set for the progress bar 
    * @param o       The object of the progress bar
    * @return        The boolean result for whether the percent was set or not 
    */
   public boolean setPercent( double percent, Object o ) {
      if ( super.setPercent( percent, o ) == true ) {
         update();
         return true;
      }
      else {
         return false;
      }
   }
   
   /** Method to update the information for the progress bar to the screen */
   private void update() {
      //further methods called here - not sure how the controller was to handle this
      progressBar.setValue((int)getRoundedPercent(1));
      messageLabel.setText(getMessage());
      messageLabel.paintImmediately( 0, 0, messageLabel.getWidth(), messageLabel.getHeight() );
      progressBar.paintImmediately( 0, 0, progressBar.getWidth(), progressBar.getHeight() );
   }

   /** 
    * Method to get the panel instance of the progress bar 
    *
    * @return The panel instance of the progress bar
    */
   public JPanel getPanel() {
      return panel;
   }

   /** 
    * Method to check whether an object instance was grabbed by the progress bar
    * 
    * @return The boolean result for whether an object instance was grabbed by the progress bar
    */
   public boolean grab( Object o ) {
      if( super.grab( o ) == true ) {
         progressBar.setStringPainted(true);
         progressBar.setVisible(true);
         cancel.setVisible(true);
         return true;
      }
      else {
         return false;
      }
   }

   /** 
    * Method to check whether an object instance was released by the progress bar
    * 
    * @return The boolean result for whether an object instance was released by the progress bar
    */
   public boolean release( Object o ) {
      if( super.release( o ) == true ) {
         progressBar.setStringPainted(false);
         setPercent(0.0, null);
         setMessage(notWorkingMessage,null);
         progressBar.setVisible(false);
         cancel.setVisible(false);
         this.thread = null;
         return true;
      }
      else {
         return false;
      }
   }

   /** 
    * Method to create a new thread for a progress bar instance
    * 
    * @return The boolean result for whether a new thread was created
    */
   public boolean registerThread(Thread thread, Object o){
      if(super.isObject(o)) {
         this.thread = thread;
         return true;
      }
      else {
         return false;
      }
   }

   /** 
    * Method to immediately stop the current thread action used by the progress bar
    */
   public void stop(){
      super.stop();
      if(thread != null) thread.interrupt();
   }

   // Mouse methods - use e.getX()
   public void mouseMoved(MouseEvent e) {}
   public void mouseDragged(MouseEvent e){}
   public void mouseClicked(MouseEvent e){
      if(e.getSource() == cancel){
         System.out.println("Stop!");
         stop();
      }
   }
   public void mouseEntered(MouseEvent e){}
   public void mouseExited(MouseEvent e){}
   public void mousePressed(MouseEvent e){}
   public void mouseReleased(MouseEvent e){}


}
