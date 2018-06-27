package timecompare;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeCompare {
	 public static void main( String args[] ) { 
		 
		 if (args.length < 11 )
		 {
			 System.out.println ("usage: timcompare year month day hour minute second year month day hour minute second");
			 System.exit(1);
		 }
		 String year1=args[0];
		 String month1=args[1];
		 String day1=args[2];
		 String hour1=args[3];
		 String minute1=args[4];
		 String second1=args[5];
		 
		 String year2=args[6];
		 String month2=args[7];
		 String day2=args[8];
		 String hour2=args[9];
		 String minute2=args[10];
		 String second2=args[11];
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		 String dateInString1 =day1+"-"+month1+"-"+year1+ " "+hour1+":"+minute1+":"+second1;
		 String dateInString2 =day2+"-"+month2+"-"+year2+ " "+hour2+":"+minute2+":"+second2;
		 try
		 {
		 Date date1 = sdf.parse(dateInString1);
		 //System.out.println(date1);
		 
		 Date date2 = sdf.parse(dateInString2);
		 //System.out.println(date2);
		
		 long diff= date2.getTime() - date1.getTime();
		 System.out.println(diff/1000);
		 
		 }
		 catch (Exception e )  {
			 System.err.println(e.getStackTrace()); 
			 }
		 
	 }

}
