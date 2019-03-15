package mongoexample;


import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcernError;
//import com.mongodb.MongoException;

/**
 * Java + MongoDB Hello world Example
 *
 */
public class mongoExamlpe {
  public static void main(String[] args) {

    try {

	/**** Connect to MongoDB ****/
	// Since 2.10.0, uses MongoClient
	//MongoClient mongo = new MongoClient("ing04esbsand01", 27017);
    	MongoCredential credential = MongoCredential.createCredential("ucmp-test", "ucmp-test", "Ehaneboke950".toCharArray());
    	MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27016), Arrays.asList(credential));
	/**** Get database ****/
    	
	// if database doesn't exists, MongoDB will create it for you
	DB db = mongo.getDB("ucmp-test");

	/**** Get collection / table from 'testdb' ****/
	// if collection doesn't exists, MongoDB will create it for you
	DBCollection table = db.getCollection("Customer");
	/**** Insert ****/
	long anz = table.count();
	System.out.println("Anz = " + anz);
	// create a document to store key and value
	
	BasicDBObject document = new BasicDBObject();
	//document.put("name", "mkyong");
	//document.put("age", 30);
	//document.put("createdDate", new Date());
	
	//table.insert(document, writeConcern);
	//table.insert(document);

	/**** Find and display ****/
	BasicDBObject searchQuery = new BasicDBObject();
	//searchQuery.put("name", "mkyong");

	//DBCursor cursor = table.find(searchQuery);

	//while (cursor.hasNext()) {
	//	System.out.println(cursor.next());
	//}

	/**** Update ****/
	// search document where name="mkyong" and update it with new values
/*	query.put("name", "mkyong");

	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name", "mkyong-updated");

	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);

	table.update(query, updateObj);

	/**** Find and display ****/
	/*BasicDBObject searchQuery2
	    = new BasicDBObject().append("name", "mkyong-updated");

	DBCursor cursor2 = table.find(searchQuery2);

	while (cursor2.hasNext()) {
		System.out.println(cursor2.next());
	}*/

	/**** Done ****/
	System.out.println("Done");

    } catch (Exception e) {
	e.printStackTrace();
    }

  }
}