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

/** Class defining the instance for a marker object. It implements GDrawableObject */
public class GMarker implements GDrawableObject, Serializable{
   /** Declaration for a point on the map for the marker */
   private GPhysicalPoint point;
   /**
    * Method to set the point on the map to place the marker
    * @param point   The point defining position of the marker
    */
   public GMarker(GPhysicalPoint point){
      this.point = point;
   }
   /**
    * Method to get the point on the map to locate the marker
    * @return        The point on the map where the marker is
    */
   public GPhysicalPoint getPoint(){
      return point;
   }
   /** Method to set the point on the map to place the marker */
   public void setPoint(GPhysicalPoint point){
      this.point = point;
   }
   /**
    * Method defining a rectangle object for the current map view
    * @param p       The point to place the marker
    * @param zoom    The current zoom level for the map
    * @return        The rectangle object defined for the current map view
    */
   public Rectangle getRectangle(GPhysicalPoint p, int zoom){
      //check for nulls to prevent null pointer exceptions
      if(p == null) return null;

      //build a rectangle data element that represents the visible area of the screen
      Point screen = new Point(p.getPixelX(zoom), p.getPixelY(zoom));

      //get the coordinate of the point on our visible screen
      Point pointOnScreen = new Point(point.getPixelX(zoom) - screen.x, point.getPixelY(zoom) - screen.y);

      return new Rectangle(pointOnScreen.x - 5,pointOnScreen.y - 5,10,10);
   }
   /**
    * Method to draw the marker object to the screen
    * @param image   The map image to be rendered
    * @param p       The point to place the marker
    * @param zoom    The current zoom level for the map
    */
   public void draw(BufferedImage image, GPhysicalPoint p, int zoom){
      //check for nulls to prevent null pointer exceptions
      if(p == null || image == null) return ;

      //build a rectangle data element that represents the visible area of the screen
      Rectangle screen = new Rectangle(p.getPixelX(zoom), p.getPixelY(zoom), image.getWidth(), image.getHeight());

      //if the point is not on the screen return here
      if(!screen.contains(point.getPixelX(zoom), point.getPixelY(zoom))) return ;

      //create a graphics context
      Graphics2D g = image.createGraphics();

      //get the coordinate of the point on our visible screen
      Point pointOnScreen = new Point(point.getPixelX(zoom) - screen.x, point.getPixelY(zoom) - screen.y);

      //draw it
      g.setColor(new Color(0,0,155));
      g.fillOval(pointOnScreen.x - 5,pointOnScreen.y - 5,10,10);
   }

}