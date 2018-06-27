package memusage;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import java.util.Arrays;

public class MemUsage {

   public static void main( String args[] ) {
	
      try{
		
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "test" );
         System.out.println("Connect to database successfully");
         //boolean auth =  db.authenticate("", "".toCharArray());db.
         
         //System.out.println("Authentication: "+auth);
         DBCollection coll = db.getCollection("testCollection");
         mongoClient.setWriteConcern(WriteConcern.JOURNALED);
         BasicDBObject doc = new BasicDBObject("name", "MongoDB")
        	        .append("type", "database")
        	        .append("count", 1)
        	        .append("info", new BasicDBObject("x", 203).append("y", 102));
        	coll.insert(doc);
        	for (int i=0; i < 100000; i++) {
        		doc=new BasicDBObject("name", "MongoDB")
            	        .append("type", "database")
            	        .append("count", i);
        	    //coll.insert(new BasicDBObject("i", i));
            	  coll.insert(new BasicDBObject(doc));
        	}
        	DBObject myDoc = coll.findOne();
        	System.out.println(myDoc);
        	System.out.println("Anzahl der Dokumente "+ coll.getCount());
         
      }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }
}