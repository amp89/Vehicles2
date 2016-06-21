package data;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class VehiclesMongoDBDAO implements VehiclesDAO {

//	final String DB_NAME = "vehicleTestOne";
	final String DB_NAME = "vehicleTestOne";
    final String DB_ADDRESS = "mongodb://localhost:27017";
//    final String CSV_FILE_NAME = "vehicle-specifications.csv";
    final String COLLECTION_NAME = "vehciles";
    
    MongoDatabase db = null;
    MongoClient mongoClient = null;
    MongoCollection<Document> vehicleCollection = null;
	
    public VehiclesMongoDBDAO(){
    	super();
    	System.out.println("the dao is aliveeee");
    }
    
 
    
    
    
	@PostConstruct
	public void connect(){
		try{
	    MongoClientURI dbURL = new MongoClientURI(DB_ADDRESS);
	    mongoClient =  new MongoClient(dbURL);
	    db = mongoClient.getDatabase(DB_NAME);
	    vehicleCollection = db.getCollection(COLLECTION_NAME);
	    System.out.println("CONNNNNECTED");
			
		}catch(MongoException me){
			me.printStackTrace();
		}
	}
	

    
	@PreDestroy
	public void disconnect(){
		
		try{
			mongoClient.close();
			System.out.println("DISSSCONNNNEEECCCTTEEEEEDDD");
		}catch(MongoException me){
			System.out.println(me);
		}
		
	}
	
	
	
	//TODO test method
	@Override
	public String sayHi(){
		
		return "{\"response\":\"hi!\"}";
	}
	
	//TODO test method
	//i guess i have to make a method to load a document into a vehicle
	@Override
	public List<String> test(){
		MongoCursor<Document> vCursor = vehicleCollection.find().iterator();
		List<String> results = new ArrayList<>();
		while(vCursor.hasNext()){
			results.add(vCursor.next().toJson());
		}
		return results;
		
	}
	
	@Override
	public String getVehicles() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public String getVehiclesById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByYearRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByMake() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByDriveType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByDisplacementRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByTransmission() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByCylinderRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByCylinders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByFuelType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByMpgRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByEmissionsRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByGasTax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehiclesByParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteVehicleById(String id) {
		// TODO Auto-generated method stub
		System.out.println(id);

		ObjectId oid = new ObjectId(id);
		Document v = vehicleCollection.find(Filters.eq("_id",oid)).first();
		vehicleCollection.deleteOne(v);
		System.out.println(v);
		
//		vehicleCollection.(Filters.eq("_id",id));
		
//		if(vehicleCursor.hasNext()){
//			System.out.println();
//			vehicleCollection.findOneAndDelete(vehicleCursor.next());
//		}else{
//			//TODO: its getting here, so its probably not finding stuff
//			System.err.println("Could not be deleted");
//		}
		return null;
	}

	@Override
	public String getVehicleImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMechData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEpaData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModelListByMake() {
		// TODO Auto-generated method stub
		return null;
	}

}
