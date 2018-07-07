package lifetracker;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class createTransitImage  extends HttpServlet {

	  private BufferedImage image; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		response.setContentType("image/png");
		HttpSession session = request.getSession(true);
		OutputStream outputStream = response.getOutputStream();
		String mode=request.getParameter("mode");
	   // java.util.Hashtable hash = (java.util.Hashtable) session.getAttribute("hash");
		java.util.Hashtable hash = new java.util.Hashtable();
		hash.put("name", request.getParameter("name"));
		hash.put("starttime_str", request.getParameter("starttime"));
		hash.put("endtime_str", request.getParameter("endtime"));
		hash.put("duration_str", request.getParameter("duration"));
		performTask(request, response,outputStream,hash);
		//createDataset(chartVec);

	}
	public void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		//performTask(request, response);

	}
	
	public void performTask(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, OutputStream outputStream , java.util.Hashtable hash) 
	{
		
		  
		
			    try {
			       // image = ImageIO.read(new URL(
			                //"http://sstatic.net/stackoverflow/img/logo.png"));
			              //  image = ImageIO.read(new File ("c:\\temp\\location.jpg"));
			    	       // image = ImageIO.read(new File ("/location.jpg"));
			  
			    	 image = ImageIO.read((getClass().getClassLoader().getResourceAsStream("location.jpg")));
			    	
			    image = process(image,hash);
			    ImageIO.write(image, "png", outputStream);
			
	  } catch (IOException e) {
	        e.printStackTrace();
	    }
}
			
			
private BufferedImage process(BufferedImage old , java.util.Hashtable hash) { 
    int w = old.getWidth();
    int h = old.getHeight();
   // System.out.println("In create Chart");
    BufferedImage img = new BufferedImage(
            w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = img.createGraphics();
    
    g2d.drawImage(old, 0, 0, null);
    g2d.setPaint(Color.black);
    g2d.setFont(new Font("Serif", Font.BOLD, 30));
    String s = (String) hash.get("name");
    String s2 = (String) hash.get("starttime_str");
    String s3 = (String) hash.get("endtime_str");
    String s4 = (String) hash.get("duration_str");
    FontMetrics fm = g2d.getFontMetrics();
    //int x = img.getWidth() - fm.stringWidth(s) - 5;
    //int y = fm.getHeight();
    int x =  img.getWidth()/2  - fm.stringWidth(s)/2 ;
    int y = img.getHeight()/ 2 ;
    g2d.drawString(s, x, y);
    
    g2d.setPaint(Color.blue);
    g2d.setFont(new Font("Serif", Font.BOLD, 25));
    x =  img.getWidth()/2  - fm.stringWidth(s2)/2;
   // y =  img.getHeight()/ 2  + fm.getHeight();
    y =  img.getHeight()/ 2  +25; 
    g2d.drawString(s4, x, y);
    
    g2d.setPaint(Color.black);
    g2d.setFont(new Font("Serif", Font.BOLD, 20));
    x =  img.getWidth()/2  - fm.stringWidth(s2)/2;
    y =  fm.getHeight(); 
    
    g2d.drawString(s2, x, y);
    y = img.getHeight() - fm.getHeight()+20; 
    g2d.drawString(s3, x, y);
    
    g2d.dispose();
    return img;
}
}
