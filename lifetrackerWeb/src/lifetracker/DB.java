package lifetracker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

public class DB {
	protected Connection con = null;
    protected boolean debug=false;
	/**
	 * Macht den INI-Hash in der Klasse "global" und stellt die Verbindung zum
	 * Datenbank-Server her. 
	 */

	


	public boolean dataBaseConnect(String username,String password, String connectString) {
		if (debug) if (debug) System.out.println("Verbinde mich zur Datenbank");
		try {
			try {
				Class.forName("org.gjt.mm.mysql.Driver").newInstance(); // DB-
																		// Treiber
																		// laden
			} catch (Exception E) {
				System.err
						.println("Konnte MySQL Datenbank-Treiber nicht laden!");
				return false;
			}
			//String url = "jdbc:mysql://192.168.2.8/budget_test";
			con = DriverManager.getConnection(connectString, username, password); // Verbindung
		      													// herstellen
			if (debug) System.out.println("Verbindung erstellt");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Treiber fuer mySQL nicht gefunden");
			return false;
		}
		return true;
	}
	
	/**
	 * Schlie�t die Verbindung zum Server. Das Objekt ist danach unbrauchbar.
	 */

	public boolean closeConnection() {
		if (con != null) {
			try {
				con.close();
				if (debug) System.out.println("Verbindung beendet");
			} catch (Exception e) {
				System.err.println("Konnte Verbindung nicht beenden!!");
				return false;
			}
		}
		return true;
	}
	
	public Vector getDayStoryline(String date) {
		Vector vec = new Vector();
		try {

			PreparedStatement stmt;
			ResultSet res = null;
			stmt = con
					.prepareStatement("select id,startdate,starttime,enddate,endtime,duration,name,mode from storyline where enddate="+date+" order by endtime");
			res = stmt.executeQuery();
			while (res.next()) {
				Hashtable hash = new Hashtable();
				hash.put("id", new Integer(res.getInt("id")));
				hash.put("startdate", (Date)  res.getDate("startdate"));
				hash.put("starttime", (java.util.Date) res.getTime("starttime"))  ;
				hash.put("enddate", (Date) res.getDate("enddate"));
				hash.put("endtime", (java.util.Date) res.getTime("endtime"))   ;
				hash.put("duration", new Integer(res.getInt("duration")));
				hash.put("name", (String) res.getString("name"));
				hash.put("mode", new Integer(res.getInt("mode")));
				
				//System.out.println("Date : " + (java.util.Date)  (res.getTime(("starttime")))) ;
				vec.addElement(hash);
			}
		} catch (SQLException e) {
			System.err.println("Konnte Select-Anweisung nicht ausführen" + e);
			return vec;
		}
		if (debug) System.out.println("Select-Anweisung ausgeführt");
		// return summe/(float)getAnz(tag,monat,year);
		return vec;
	}
	
	
	public Vector getPeriodStoryline(String startdate, String enddate) {
		Vector vec = new Vector();
		String str="select id,startdate,starttime,enddate,endtime,duration,name,mode,dist from storyline where enddate >= "+ startdate + " and "
				+ "startdate <=" + enddate+" order by startdate,starttime";
		try {

			PreparedStatement stmt;
			ResultSet res = null;
			stmt = con
					.prepareStatement(str);
			res = stmt.executeQuery();
			while (res.next()) {
				//System.out.println("Erg:");
				Hashtable hash = new Hashtable();
				hash.put("id", new Integer(res.getInt("id")));
				hash.put("startdate", (Date)  res.getDate("startdate"));
				hash.put("starttime", (java.util.Date) res.getTime("starttime"))  ;
				hash.put("enddate", (Date) res.getDate("enddate"));
				hash.put("endtime", (java.util.Date) res.getTime("endtime"))   ;
				hash.put("duration", new Long(res.getLong("duration")));
				hash.put("name", (String) res.getString("name"));
				hash.put("mode", new Integer(res.getInt("mode")));
				hash.put("dist", new Float(res.getFloat("dist")));
				
				//System.out.println("Date : " + (java.util.Date)  (res.getTime(("starttime")))) ;
				vec.addElement(hash);
			}
		} catch (SQLException e) {
			System.err.println("Konnte Select-Anweisung nicht ausführen" + e);
			return vec;
		}
		if (debug) System.out.println("Select-Anweisung ausgeführt :" + str);
		// return summe/(float)getAnz(tag,monat,year);
		return vec;
	}
	
	public static java.util.Date convertFromSQLDateToJAVADate(
            java.sql.Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return sqlDate;
    }
}
