package lifetracker;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class Main
extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		performTask(request, response);

	}

	public void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		performTask(request, response);

	}

	public void performTask(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {
		try {
			//FileHandling fh = new FileHandling();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession(true);
			String info = request.getParameter("info");
			ServletContext context = getServletContext();
			
			
			//HeaderFooter hf = new HeaderFooter();
			//out.println("<html>");
			out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"de\" lang=\"de\">");
			out.println("<head>");
			out.println(" <title>Lifetracker</title>");
			out.println("<script src=\"datechooser/date-functions.js\" type=\"text/javascript\"></script>");
			out.println("<script src=\"datechooser/datechooser.js\" type=\"text/javascript\"></script>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"datechooser/datechooser.css\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Lifetracker</h1>");
			
			writeStoryline(out,context,request);
			
			
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
		
		catch (Throwable theException) {
			theException.printStackTrace();
		}
	}
	public void writeStoryline(PrintWriter out,ServletContext context,javax.servlet.http.HttpServletRequest request )
	{
		HttpSession session = request.getSession(true);
		DB db = new DB();
		Vector storyline = new Vector();
		String DBusername = context.getInitParameter("DBusername");
		String DBuserpassword = context.getInitParameter("DBpassword");
		String DBconnectString = context.getInitParameter("DBconnectstring");
		System.out.println("Connectstring= "+DBconnectString);
		db.dataBaseConnect(DBusername, DBuserpassword, DBconnectString);
		Date date = new Date();
		SimpleDateFormat sdfDat = new SimpleDateFormat(
				"yyyyMMdd");
		String strdate= sdfDat.format(date);
		//strdate = "20151107";
		
		//storyline = db.getDayStoryline(strdate);
		String start="20151119";
		String end="20151119";
		start= (String)request.getParameter("start");
		//System.out.println("Start = "+start);
		if (start==null)
		{
			start=strdate;
			
		}
		System.out.println("Start = "+start);
		end = (String)request.getParameter("end");
		
		if (end==null)
		{
			end=strdate;
		}
		System.out.println("End = "+end);
		storyline=db.getPeriodStoryline(start,end);
		System.out.println(" Anzahl der Stories: "+storyline.size());
		db.closeConnection();
		
		out.println("<form action=\"lifetracker\">");
		//out.println("<input type=\"hidden\" name=\"mode\" value=\"allSensors\">");
		out.println("<input type=\"submit\" value=\"Auswertung für Zeitraum \">");

		out.println("Start: <input id=\"dob1\" name=\"start\" size=\"10\" maxlength=\"10\" type=\"text\" value=\""+start+"\" /><img src=\"datechooser/calendar.gif\" onclick=\"showChooser(this, 'dob1', 'chooserSpan', 2005, 2050, 'Ymd', false);\"/>");
		out.println("<div id=\"chooserSpan\" class=\"dateChooser select-free\" style=\"display: none; visibility: hidden; width: 160px;\">");
		out.println("</div>");
		out.println("End: <input id=\"dob2\" name=\"end\" size=\"10\" maxlength=\"10\" type=\"text\" value=\""+end+"\" /><img src=\"datechooser/calendar.gif\" onclick=\"showChooser(this, 'dob2', 'chooserSpan', 2005, 2050, 'Ymd', false);\"/>");
		out.println("<div id=\"chooserSpan\" class=\"dateChooser select-free\" style=\"display: none; visibility: hidden; width: 160px;\">");
		out.println("</div>");
		
		out.println("</form>");
		//session.setAttribute("start", start);
		//session.setAttribute("end", end);
		
		out.println("<table cellpadding=\"0\" cellspacing=\"4\" width=\"114\" border=\"0\">");
		Vector line= consolidateVector(storyline,start,end);
		
		for (int i=0; i<line.size();i++)
		{
			Hashtable hash = (Hashtable) line.elementAt(i);
			if (  (Integer) hash.get("mode")==0)
			{
				continue;
			}
			session.setAttribute("hash", hash);
			String name= (String) hash.get("name");
			String starttime = (String) hash.get("starttime_str");
			String endtime= (String) hash.get("endtime_str");
			String  duration=(String) hash.get("duration_str");
			
			out.println("<tr><td>");
			
			/*out.println(hash.get("name"));
			out.println("</td><td>");
			out.println(hash.get("starttime_str"));
			out.println("</td><td>");
			out.println(hash.get("endtime_str"));
			out.println("</td><td>");
			out.println(hash.get("duration_str"));
			out.println("</td><td>");
			out.println(hash.get("dist_str"));
			out.println("</td><td>");
			out.println(hash.get("transDuration_str"));
			out.println("</td></tr>");
			out.println("<tr><td>");*/
			out.println("<p><img src=transitchart?name="+name+"&starttime="+starttime+"&endtime="+endtime+"&duration="+duration+  " width'100' height='150'>");
			
			out.println("</td></tr>");
			out.println("<tr><td>");
			
			out.println(" <b> Transit </b> Lämge: " + hash.get("dist_str") + " Km Dauer: "+hash.get("transDuration_str") );
			out.println("</td></tr>");
		}
		out.println("</table>");
		
		//Insgesamt
		out.println("<b> Dauer insgesamt: </b>"); 
		out.println("<table cellpadding=\"0\" cellspacing=\"4\" width=\"114\" border=\"0\">");
  Hashtable sum = compSum(line);	
  
  Enumeration<String> keys = sum.keys();
  while(keys.hasMoreElements()){
      String key = keys.nextElement();
      out.println("<tr><td>");
      out.println(key);
      out.println("</td><td>");
      out.println();
			long duration = (Long) sum.get(key);
			int days = (int) (duration / (60 * 60 * 24));
			//int hours = (int) (duration / (60 * 60) % 24);
			int hours = (int) (duration / (60 * 60));
			int minutes = (int) (duration / (60) % 60);
			int seconds = (int) (duration % 60);
			out.println(hours + ":" + minutes + ":" + seconds);
			out.println("</td></tr>");
		}
		out.println("</table>");
  
  
	}
	/**
	 * Macht einen Vector mit nur Lokationszeiten
	 * @param src Sourse Vector
	 * @param start Starttag
	 * @param end Endtag
	 * @return Vector mit nur Lokationszeiten und Optimiert
	 */
	private Vector consolidateVector(Vector src,String start, String end)
	{
		Vector line = new Vector();
		SimpleDateFormat formaterDate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formaterTime = new SimpleDateFormat("HH:mm:ss");
		Hashtable oldhash= null;
		Date startday = new Date();
		Date endday = new Date();
		try {
		startday= (Date)formaterDate.parse(start);
		endday= (Date)formaterDate.parse(end);
		} catch (Exception e) {e.printStackTrace();}	
		
		for (int i=0 ; i< src.size() ; i++)
		{
			
			System.out.println("++++++Eintrag++++++ " +i);
			//Stelle die erst Zeit des Vectors auf 0 Uhr, falls mode = 1 ist;
			Hashtable hash = (Hashtable) src.elementAt(i);
			Calendar cal_akt= Calendar.getInstance();
		 	Date now = cal_akt.getTime();
			if (  (Integer) hash.get("mode")==0)
			{
				if (line.size()==0)
				{
				continue;
				}
				DecimalFormat df = new DecimalFormat("0.00");
				oldhash.put("transDuration", hash.get("duration"));
				oldhash.put("transDist", hash.get("dist"));
				oldhash.put("dist_str", df.format((float)hash.get("dist")/1000));
				long duration = (long)hash.get("duration");
				 int days = (int)(duration / ( 60 * 60 * 24));
				    int hours = (int)(duration / ( 60 * 60) % 24);
				    int minutes = (int)(duration / ( 60) % 60);
				    int seconds = (int)(duration  % 60);
			 oldhash.put("transDuration_str" ,""+hours+":"+minutes+":"+ seconds);
			 continue;
			}
			
			Date startdate = (Date) hash.get("startdate");
			Date enddate = (Date) hash.get("enddate");
			//System.out.println("Start 1 = "+ formaterDate.format(startday));
			//System.out.println("Start 2 = "+ formaterDate.format( (Date) hash.get("startdate")));
			
			
			  
			  DateTime ystartdate = DateTime.parse(formaterDate.format(hash.get("startdate"))+formaterTime.format(hash.get("starttime")), 
	                  DateTimeFormat.forPattern("yyyyMMddHH:mm:ss"));
			  DateTime ystartday =DateTime.parse(start+"00:00:00", 
	                  DateTimeFormat.forPattern("yyyyMMddHH:mm:ss"));
			  
			 if (ystartdate.isBefore(ystartday))  
			//if (startdate.getDay() < startday.getDay()) 
			{
				try {
					System.out.println("Starttime auf 0 gesetzt");
					hash.put("starttime",(Date)formaterTime.parse("00:00:00") );
					hash.put("startdate",startday);
				} catch (Exception e) {e.printStackTrace(); } 
			}
			 DateTime yenddate = DateTime.parse(formaterDate.format(hash.get("enddate"))+formaterTime.format(hash.get("endtime")), 
	                  DateTimeFormat.forPattern("yyyyMMddHH:mm:ss"));
			  DateTime yendday =DateTime.parse(end+"23:59:59", 
	                  DateTimeFormat.forPattern("yyyyMMddHH:mm:ss"));
			//if (enddate.getDay() > endday.getDay())
			  if (yenddate.isAfter(yendday))
			{
				try {
					System.out.println("Endtime auf 0 gesetzt");
					hash.put("endtime",(Date)formaterTime.parse("23:59:59") );
					hash.put("enddate",endday );
					yenddate = DateTime.parse(formaterDate.format(hash.get("enddate"))+formaterTime.format(hash.get("endtime")), 
			                  DateTimeFormat.forPattern("yyyyMMddHH:mm:ss"));// für yenddate.getDayOfYear() == ynow.getDayOfYear(
				} catch (Exception e) {e.printStackTrace(); } 
			}
			
			  DateTime ynow =DateTime.parse(formaterDate.format(now)+"00:00:00", 
	                  DateTimeFormat.forPattern("yyyyMMddHH:mm:ss"));
			//if (enddate.getDay() == now.getDay())
			//TODO fixme
			  if (yenddate.getDayOfYear() == ynow.getDayOfYear())
			{
				 if (i== src.size()-1)
				 {
				hash.put("endtime",now );
				System.out.println("Endtime auf jetzt gesetzt");
				 }
			}
			
			hash.put("starttime_str",formaterTime.format( (Date) hash.get("starttime") ));
			hash.put("endtime_str",formaterTime.format( (Date) hash.get("endtime") ));
			
			//int duration= ((Integer) hash.get("duration")).intValue() ;
			//Date d1 = (Date) hash.get("starttime");
			    
			//Date d2 = (Date) hash.get("endtime");
			Date d1 = makeDate ((Date)hash.get("startdate"),(Date)hash.get("starttime")); 
			Date d2 = makeDate ((Date)hash.get("enddate"),(Date)hash.get("endtime")); 
			
			long duration = ( d2.getTime()  - d1.getTime()) / 1000; 
			
			System.out.println("Duration= "+duration);
			
			    int days = (int)(duration / ( 60 * 60 * 24));
			    int hours = (int)(duration / ( 60 * 60) % 24);
			    int minutes = (int)(duration / ( 60) % 60);
			    int seconds = (int)(duration  % 60);
			   
				hash.put("duration",duration); 
			    hash.put("duration_str" ,""+hours+":"+minutes+":"+ seconds);
			    System.out.println("Duration_str=  "+hours+":"+minutes+":"+ seconds);
			    if (oldhash != null)
			    {
			    	//d1 = (Date) oldhash.get("endtime");
			    	d2 = makeDate ((Date)hash.get("startdate"),(Date)hash.get("starttime")); 
			    	d1 = makeDate ((Date)oldhash.get("enddate"),(Date)oldhash.get("endtime")); 
			    	//d2= (Date) hash.get("starttime");
			    	
			    	System.out.println("d1 = " + d1.getTime());
			    	
			    	System.out.println("d2 = " + d2.getTime());
			    	duration = ( d2.getTime()  - d1.getTime()) / 1000; 
			    	System.out.println("Duration to merge = " + duration);
			    	System.out.println("Location to merge = " + hash.get("name") +","+oldhash.get("name"));
			    	if (duration < 400 && hash.get("name").equals(oldhash.get("name")))
			    	{
			    		System.out.println("Eintrag wird gemerged ");
			    		oldhash.put("endtime", hash.get("endtime"));
			    		oldhash.put("enddate", hash.get("enddate"));
			    		oldhash.put("endtime_str", hash.get("endtime_str"));
			    		
			    		//d1 = (Date) oldhash.get("starttime");
						//d2 = (Date) oldhash.get("endtime");
			    		d1 = makeDate ((Date)oldhash.get("startdate"),(Date)oldhash.get("starttime")); 
						d2 = makeDate ((Date)oldhash.get("enddate"),(Date)oldhash.get("endtime")); 
				    	//d2= (Date) hash.get("starttime");
				    	
						duration = ( d2.getTime()  - d1.getTime()) / 1000; 
						days = (int)(duration / ( 60 * 60 * 24));
						hours = (int)(duration / ( 60 * 60) % 24);
						minutes = (int)(duration / ( 60) % 60);
						seconds = (int)(duration  % 60);
						oldhash.put("duration",duration); 
						oldhash.put("duration_str" ,""+hours+":"+minutes+":"+ seconds);   
						continue;
			    	}
			    	
			    }
			    		
			oldhash=hash;    
			line.addElement(hash);
		}//for
		return line;
		
		
		/*cal_begin.setTime(formater.parse(starttimestr));
		Date d1 = cal_begin.getTime();
	
		
		cal_end.setTime(formater.parse(endtimestr));
		Date d2 = cal_end.getTime();
		 //d1 = formater.parse(starttimestr);
		 //d2 = formater.parse(endtimestr);
		
	    diff = (d2.getTime() - d1.getTime()) / 1000 ;*/
		
		/*else
		{
			String starttime=formaterTime.format(((Date)hash.get("starttime")));
		}*/
		 
		
	}
	private Date makeDate (Date dat, Date time)
	{
		try {
		SimpleDateFormat formaterDate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formaterTime = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		//SimpleDateFormat formaterTime = new SimpleDateFormat("HH:mm:ss");
		String tag = formaterDate.format(dat);
		//System.out.println(" Tag = " +tag);
		String zeit =formaterTime.format(time);
		//System.out.println(" Zeit = " +zeit);
		return formater.parse(tag+zeit);
		} catch (Exception e) {e.printStackTrace();}
	    return new Date();			
	}
	
	private Hashtable  compSum( Vector line)
	{
		Vector vec = new Vector();
		Hashtable hash = new Hashtable();
		for (int i=0; i< line.size(); i++)
		{
			Hashtable newhash = (Hashtable) line.elementAt(i);
			//System.out.println(newhash);
	        String name = (String) newhash.get("name");
	        if (hash.containsKey(name))
	        {  
	        	System.out.println("Found: Name = "+name);
	       long oldduration= (Long)hash.get(name);
	   //	System.out.println("Oldduration = "+oldduration);
	       long newduration=(Long) newhash.get("duration");
	      // System.out.println("Newduration = "+newduration);
	        	hash.put(name, oldduration+newduration);
	        	
	        }
	        else
	        	
	        {
	        	hash.put(name,newhash.get("duration"));
	        //	 System.out.println("First for " + name +"  = "+newhash.get("duration") );
	        }
		}
		return hash;
	}
}
	
