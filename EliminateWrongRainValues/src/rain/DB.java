package rain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

public class DB {
		boolean debug=true; 
		protected Connection con = null;
		
		public boolean dataBaseConnect(String username,String password, String connectString) {
			if (debug) if (debug) System.out.println("Verbinde mich zur Datenbank");
			try {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); // DB-
																			// Treiber
																			// laden
				} catch (Exception E) {
					System.err
							.println("Konnte MySQL Datenbank-Treiber nicht laden!");
					return false;
				}
				//String url = "jdbc:mysql://192.168.2.8/aquarium";
				con = DriverManager.getConnection(connectString, username, password); // Verbindung
			      													// herstellen
				//con = DriverManager.getConnection(url, "aquarium", "aquarium"); 
				if (debug) System.out.println("Verbindung erstellt");
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Treiber fuer mySQL nicht gefunden");
				return false;
			}
			return true;
		}
		
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
		
		public Vector getRain() {
			Vector vec = new Vector();
			try {

				PreparedStatement stmt;
				ResultSet res = null;
				String str_stm;
					str_stm=("select id,datum,regen  from data_2018");
				if (debug) System.out.println(str_stm);
				stmt = con
						.prepareStatement(str_stm);
				res = stmt.executeQuery();
				while (res.next()) {
					Hashtable hash = new Hashtable();
					hash.put("id", new Integer(res.getInt("id")));
					hash.put("regen", (Double) res.getDouble("regen"));
					hash.put("datum", (String)( res.getString("datum")));
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
		public boolean setRain(Double rain,int id) {
			try {
				
				String str= "update data_2018 set regen = "+rain+" where id = " +id;
				if (debug) System.out.println(str);
				PreparedStatement stmt;
				stmt = con.prepareStatement(str);
				stmt.executeUpdate();
			} catch (SQLException e) {
				System.err.println("Konnte Update-Anweisung nicht ausführen" + e);
			    return false;
			}
			return true;
		}


}
