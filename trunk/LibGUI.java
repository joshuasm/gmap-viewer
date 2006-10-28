
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




class LibGUI{

   public static BufferedImage loadImage(String location, Dimension dimension){
      try{
         // load image from INFILE
         Image image = Toolkit.getDefaultToolkit().createImage(location);
         MediaTracker mediaTracker = new MediaTracker(new Container());
         mediaTracker.addImage(image, 0);
         mediaTracker.waitForID(0);

         //initialize dimension if its null
         if(dimension == null) dimension = new Dimension(image.getWidth(null), image.getHeight(null));

         //create buffered image
         BufferedImage returnImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
         Graphics2D graphics2D = returnImage.createGraphics();

         //draw buffered Image
         graphics2D.drawImage(image, 0, 0, dimension.width, dimension.height, null);
         //return it
         return returnImage;

      }catch(Exception e){return null;}
   }

   public static BufferedImage loadImage(String location){
      return loadImage(location,null);
   }



   public static String getURL(String urlString, String getData, String postData){
      try{
         if(getData != null)
            if(!getData.equals(""))
               urlString += "?" + getData;
         URL url = new URL(urlString);
         URLConnection connection = url.openConnection();
         connection.setDoOutput(true);
         PrintWriter out = new PrintWriter(connection.getOutputStream());
         out.print(postData);
         out.close();
         BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

         int inputLine;
         String output = "";
         while ((inputLine = in.read()) != -1) output += (char)inputLine;
         in.close();
         return output;
      }catch (Exception e){
         return null;
      }
   }

   public static String getURL(String urlString){
      return getURL(urlString, "", "");
   }

   public static long getTime(){
      Date date = new Date();
      return date.getTime();
   }

}