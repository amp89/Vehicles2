package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import entities.VehicleFormData;

public class VehiclesMongoDBDAO implements VehiclesDAO {

//	final String DB_NAME = "vehicleTestOne";
	final String DB_NAME = "vehicleTestTwo";
    final String DB_ADDRESS = "mongodb://localhost:27017";
//    final String CSV_FILE_NAME = "vehicle-specifications.csv";
    final String COLLECTION_NAME = "vehicles";
    
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
	public String getVehiclesById(String id) {
		//TODO fix this to get multiples, or just delete it
		System.out.println("get vehicles by id");
		System.out.println(id);
		ObjectId oid = new ObjectId(id);
		System.out.println(oid);
		Document vehicle = vehicleCollection.find(Filters.eq("_id", oid)).first();
		
		// TODO Auto-generated method stub
		return vehicle.toJson();
	}
	
	@Override
	public String getVehicleById(String id) {
		System.out.println("get vehicles by id");
		System.out.println(id);
		ObjectId oid = new ObjectId(id);
		System.out.println(oid);
		Document vehicle = vehicleCollection.find(Filters.eq("_id", oid)).first();
		
		return vehicle.toJson();
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
	public String getModelListByMake(String make) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String updateVehicle(VehicleFormData vfd){
		ObjectId oid = new ObjectId(vfd.getId());
		System.out.println(oid);
		Document vehicleToUpdate = vehicleCollection.find(Filters.eq("_id", oid)).first();
		System.out.println("origin: " + vehicleToUpdate);
		Document convertedVehicle = convertFormDataToDocument(vfd);
		System.out.println("new: " + convertedVehicle);

		
		vehicleCollection.replaceOne(Filters.eq("_id", oid), new Document(convertFormDataToMap(vfd)));
		
		
		
		
		return "Updated";
	}

	private Document convertFormDataToDocument(VehicleFormData vfd){
		Map<String,Object> vehicleDataMap = new HashMap<>();

		vehicleDataMap.put("year", vfd.getYear());
		vehicleDataMap.put("make", vfd.getMake().trim());
		vehicleDataMap.put("model", vfd.getModel().trim());
		Map<String,Object> mechDataMap = new HashMap<>();
		mechDataMap.put("driveType", vfd.getDriveType().trim());
		mechDataMap.put("displacement", vfd.getDisplacement());
		mechDataMap.put("transmissionType", vfd.getTransmissionType().trim());
		mechDataMap.put("fuelType", vfd.getFuelType().trim());
		
		
		Map<String,Object> epaDataMap = new HashMap<>();
		epaDataMap.put("cityMpg", vfd.getCityMpg());
		epaDataMap.put("highwayMpg", vfd.getHighwayMpg());
		epaDataMap.put("hasGasTax", vfd.isHasGasTax());
		epaDataMap.put("averageMpg", vfd.getAverageMpg());
		epaDataMap.put("emissions", vfd.getEmissions());
		
		vehicleDataMap.put("epaData", epaDataMap);
		vehicleDataMap.put("mechData", mechDataMap);
		
		Document convertedVehicle = new Document(vehicleDataMap);
		return convertedVehicle;
	}
	
	private Map<String,Object> convertFormDataToMap(VehicleFormData vfd){
		Map<String,Object> vehicleDataMap = new HashMap<>();
		
		vehicleDataMap.put("year", vfd.getYear());
		vehicleDataMap.put("make", vfd.getMake().trim());
		vehicleDataMap.put("model", vfd.getModel().trim());
		Map<String,Object> mechDataMap = new HashMap<>();
		mechDataMap.put("driveType", vfd.getDriveType().trim());
		mechDataMap.put("displacement", vfd.getDisplacement());
		mechDataMap.put("transmissionType", vfd.getTransmissionType().trim());
		mechDataMap.put("fuelType", vfd.getFuelType().trim());
		
		
		Map<String,Object> epaDataMap = new HashMap<>();
		epaDataMap.put("cityMpg", vfd.getCityMpg());
		epaDataMap.put("highwayMpg", vfd.getHighwayMpg());
		epaDataMap.put("hasGasTax", vfd.isHasGasTax());
		epaDataMap.put("averageMpg", vfd.getAverageMpg());
		epaDataMap.put("emissions", vfd.getEmissions());
		
		vehicleDataMap.put("epaData", epaDataMap);
		vehicleDataMap.put("mechData", mechDataMap);
		
		Document convertedVehicle = new Document(vehicleDataMap);
		return vehicleDataMap;
	}
}
