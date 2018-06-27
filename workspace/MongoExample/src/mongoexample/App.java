package mongoexample;


import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;


import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
 
public class App{
 

	DBObject doc = createDBObject();
	
    public static void main(String[] args) {
        try {
             
            //MongoClient mongoClient = new MongoClient("ing04esbsand01");
        	MongoClient mongoClient = new MongoClient("localhost");
             
            List<String> databases = mongoClient.getDatabaseNames();
             
            MongoDatabase database = mongoClient.getDatabase("testdb"); 
           
             database.createCollection("sampleCollection"); 
            System.out.println("Collection created successfully"); 
        /*    for (String dbName : databases) {
                System.out.println("- Database: " + dbName);
                 
                DB db = mongoClient.getDB(dbName);
                DBCollection col = db.getCollection("users");
                
                
                Set<String> collections = db.getCollectionNames();
                for (String colName : collections) {
                    System.out.println("\t + Collection: " + colName);
                }
            }*/
             
            mongoClient.close();
             
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
    }
    private static DBObject createDBObject() {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		docBuilder.append("_id", "1");
		docBuilder.append("name","myName");
		docBuilder.append("role", "myRole");
		docBuilder.append("isEmployee", "true");
		return docBuilder.get();
	
}
}