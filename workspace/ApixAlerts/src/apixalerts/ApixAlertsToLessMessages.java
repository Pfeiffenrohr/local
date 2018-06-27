package apixalerts;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ApixAlertsToLessMessages {

    public static void main(String[] argv) {

        //System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

       // System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@ffm04esbdb03cp.media-saturn.com:1522:p01esb01", "asg", "XZz3MJma");
            Date datenow = new Date();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE,-5 );
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            //System.out.print(sdf.format(datenow) + "\n");
            Date datebefore = cal.getTime();
            //System.out.print(sdf.format(datebefore) + "\n");
            
            String selectTableSQL = "Select count(*)  from ASG_TRANSACTIONS a  "
            					+ "where  a.TRN_TIMESTAMP > to_timestamp ('"+sdf.format(datebefore)+"', 'dd.mm.rr hh24:mi:ss')" 
            					+	"and a.TRN_TIMESTAMP < to_timestamp ('"+sdf.format(datenow)+"', 'dd.mm.rr hh24:mi:ss')";
            
            //System.out.println(selectTableSQL);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
            	//String userid = rs.getString("USER_ID");
            	//String username = rs.getString("USERNAME");
            	String anz = rs.getString( "count(*)" );
            	System.out.println(sdf.format(datenow)+" " +anz);
            	
            }
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }
       
        if (connection != null) {
           // System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}