package rain;

import java.util.Hashtable;
import java.util.Vector;


public class Rain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DB db = new DB();
		Vector all = new Vector();
	//Connect to database
		db.dataBaseConnect("wetter", "wetter", "jdbc:mysql://localhost/wetter");
		all=db.getRain();
		Double rain=0.0;
		String datum;
		int id;
		Double old_rain=0.0;
		int count=0;
		for (int i=0; i<all.size();i++)
		{
			
			rain= (Double)((Hashtable)all.get(i)).get("regen");
			datum=(String)((Hashtable)all.get(i)).get("datum");
			id=((Integer)((Hashtable)all.get(i)).get("id")).intValue();
			//System.out.println(rain);
			if (i==0)
			{
				old_rain=rain;
				continue;
			}
			/*if (((old_rain-1)<10580.6) && ((old_rain+1)>10580.6))
			{
				
				System.out.println("Rain -1 "+rain+" "+i);
				
				old_rain=rain;
				//break;
				continue;
			}*/
			if (rain-old_rain > 50 || rain-old_rain < -50)
			{
			
				System.out.println("old ="+old_rain);
				System.out.println("new ="+rain);
				//rm id
				System.out.println("update "+datum +" "+rain+" with "+old_rain);
				db.setRain(old_rain, id);
				count++;
			}
			else
			{
			old_rain=rain;
			}
		}
		db.closeConnection();
		System.out.println(count + " Datensätze gelöscht von "+all.size());
	}

}
