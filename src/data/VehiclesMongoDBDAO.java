package data;

import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.excludeId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

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

import entities.SearchFormVehicleData;
import entities.VehicleFormData;

public class VehiclesMongoDBDAO implements VehiclesDAO {

	// final String DB_NAME = "vehicleTestOne";
	final String DB_NAME = "vehicleTestTwo";
	final String DB_ADDRESS = "mongodb://localhost:27017";
	// final String CSV_FILE_NAME = "vehicle-specifications.csv";
	final String COLLECTION_NAME = "vehicles";

	MongoDatabase db = null;
	MongoClient mongoClient = null;
	MongoCollection<Document> vehicleCollection = null;

	public VehiclesMongoDBDAO() {
		super();
		System.out.println("the dao is aliveeee");
	}

	@PostConstruct
	public void connect() {
		try {
			MongoClientURI dbURL = new MongoClientURI(DB_ADDRESS);
			mongoClient = new MongoClient(dbURL);
			db = mongoClient.getDatabase(DB_NAME);
			vehicleCollection = db.getCollection(COLLECTION_NAME);
			System.out.println("CONNNNNECTED");

		} catch (MongoException me) {
			me.printStackTrace();
		}
	}

	@PreDestroy
	public void disconnect() {

		try {
			mongoClient.close();
			System.out.println("DISSSCONNNNEEECCCTTEEEEEDDD");
		} catch (MongoException me) {
			System.out.println(me);
		}

	}

	// TODO test method
	@Override
	public String sayHi() {

		return "{\"response\":\"hi!\"}";
	}

	// TODO test method
	// i guess i have to make a method to load a document into a vehicle
	@Override
	public List<String> test() {
		MongoCursor<Document> vCursor = vehicleCollection.find().iterator();
		List<String> results = new ArrayList<>();
		while (vCursor.hasNext()) {
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
		// TODO fix this to get multiples, or just delete it
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
	public List<String> getVehiclesByParameters(SearchFormVehicleData sfvd) {
		// TODO might change these methods to accept canned queries
		// right now they are set up so they can fix bad user input

		if (sfvd.getYearHigh() == null) {
			sfvd.setYearHigh(9999);

		}
		if (sfvd.getYearLow() == null) {
			sfvd.setYearLow(0);
		}
		List<BasicDBObject> searchParamList = new ArrayList<>();
		BasicDBObject yearLessThan = new BasicDBObject();
		yearLessThan.put("year", new BasicDBObject("$lte", sfvd.getYearHigh()));
		searchParamList.add(yearLessThan);

		BasicDBObject yearGreaterThan = new BasicDBObject();
		yearGreaterThan.put("year", new BasicDBObject("$gte", sfvd.getYearLow()));
		searchParamList.add(yearGreaterThan);

		if (!sfvd.getModel().equals("")) {
			BasicDBObject model = new BasicDBObject();
			model.put("model",
					Pattern.compile(".*" + sfvd.getModel().trim().toLowerCase() + "*.", Pattern.CASE_INSENSITIVE));
			searchParamList.add(model);
		}

		if (!sfvd.getMake().equals("")) {
			BasicDBObject make = new BasicDBObject();
			make.put("make",
					Pattern.compile(".*" + sfvd.getMake().trim().toLowerCase() + "*.", Pattern.CASE_INSENSITIVE));
			searchParamList.add(make);
		}
		if (!sfvd.getDriveType().equals("")) {
			BasicDBObject driveType = new BasicDBObject();
			System.out.println("DRIVE TYPE INPUT: " + sfvd.getDriveType());
			driveType.put("mechData.driveType",
					Pattern.compile(".*" + sfvd.getDriveType().trim().toLowerCase() + "*.", Pattern.CASE_INSENSITIVE));
			searchParamList.add(driveType);
		}

		if (sfvd.getDisplacementHigh() != null) {

			BasicDBObject displacementLessThan = new BasicDBObject();
			displacementLessThan.put("mechData.displacement", new BasicDBObject("$lte", sfvd.getDisplacementHigh()));
			searchParamList.add(displacementLessThan);
		}

		if (sfvd.getDisplacementLow() != null) {

			BasicDBObject displacementGreaterThan = new BasicDBObject();
			displacementGreaterThan.put("mechData.displacement", new BasicDBObject("$gte", sfvd.getDisplacementLow()));
			searchParamList.add(displacementGreaterThan);
		}

		if (!sfvd.getTransmissionType().equals("")) {

			BasicDBObject transmissionType = new BasicDBObject();
			transmissionType.put("mechData.transmissionType", Pattern
					.compile(".*" + sfvd.getTransmissionType().trim().toLowerCase() + "*.", Pattern.CASE_INSENSITIVE));
			searchParamList.add(transmissionType);
		}

		if (!sfvd.getFuelType().equals("")) {

			BasicDBObject fuelType = new BasicDBObject();
			fuelType.put("mechData.fuelType",
					Pattern.compile(".*" + sfvd.getFuelType().trim().toLowerCase() + "*.", Pattern.CASE_INSENSITIVE));
			searchParamList.add(fuelType);
		}

		if (sfvd.getCityMpgHigh() != null) {
			System.out.println("High city");
			BasicDBObject cityMpgLessThan = new BasicDBObject();
			cityMpgLessThan.put("epaData.cityMpg", new BasicDBObject("$lte", sfvd.getCityMpgHigh()));
			searchParamList.add(cityMpgLessThan);
		}

		if (sfvd.getCityMpgLow() != null) {
			System.out.println("LOW city");
			BasicDBObject cityMpgGreaterThan = new BasicDBObject();
			cityMpgGreaterThan.put("epaData.cityMpg", new BasicDBObject("$gte", sfvd.getCityMpgLow()));
			searchParamList.add(cityMpgGreaterThan);
		}
		
		
		
		
		if (sfvd.getHasGasTax() != null) {
			System.out.println("Has gas tx");
			BasicDBObject hasGasTax = new BasicDBObject();
			hasGasTax.put("epaData.hasGasTax", sfvd.getHasGasTax());
			searchParamList.add(hasGasTax);
		}
		
		
		if (sfvd.getEmissionsHigh() != null) {
			System.out.println("High em");
			BasicDBObject emissionsLessThan = new BasicDBObject();
			emissionsLessThan.put("epaData.emissions", new BasicDBObject("$lte", sfvd.getEmissionsHigh()));
			searchParamList.add(emissionsLessThan);
		}
		
		if (sfvd.getEmissionsLow() != null) {
			System.out.println("LOW em");
			BasicDBObject emissionsGreaterThan = new BasicDBObject();
			emissionsGreaterThan.put("epaData.emissions", new BasicDBObject("$gte", sfvd.getEmissionsLow()));
			searchParamList.add(emissionsGreaterThan);
		}
		
		
		
		if (sfvd.getHighwayMpgHigh() != null) {
			System.out.println("High hw");
			BasicDBObject highwayMpgLessThan = new BasicDBObject();
			highwayMpgLessThan.put("epaData.highwayMpg", new BasicDBObject("$lte", sfvd.getHighwayMpgHigh()));
			searchParamList.add(highwayMpgLessThan);
		}
	
		
		if (sfvd.getHighwayMpgLow() != null) {
			System.out.println("LOW hw");
			BasicDBObject highwayMpgGreaterThan = new BasicDBObject();
			highwayMpgGreaterThan.put("epaData.highwayMpg", new BasicDBObject("$gte", sfvd.getHighwayMpgLow()));
			searchParamList.add(highwayMpgGreaterThan);
		}
		
		
		if (sfvd.getAverageMpgHigh() != null) {
			System.out.println("High avg");
			BasicDBObject averageMpgLessThan = new BasicDBObject();
			averageMpgLessThan.put("epaData.averageMpg", new BasicDBObject("$lte", sfvd.getAverageMpgHigh()));
			searchParamList.add(averageMpgLessThan);
		}
		
		
		if (sfvd.getAverageMpgLow() != null) {
			System.out.println("LOW avg");
			BasicDBObject averageMpgGreaterThan = new BasicDBObject();
			averageMpgGreaterThan.put("epaData.highwayMpg", new BasicDBObject("$gte", sfvd.getAverageMpgLow()));
			searchParamList.add(averageMpgGreaterThan);
		}
		
		
		
		
	
		
		
		

		// BasicDBObject yearGreaterThan = new BasicDBObject();
		// yearGreaterThan.put("year",new
		// BasicDBObject("$gte",sfvd.getYearLow()));
		// searchParamList.add(yearGreaterThan);
		//
		//
		// BasicDBObject yearGreaterThan = new BasicDBObject();
		// yearGreaterThan.put("year",new
		// BasicDBObject("$gte",sfvd.getYearLow()));
		// searchParamList.add(yearGreaterThan);
		//
		//

//		DBObject query = new BasicDBObject();

		MongoCursor<Document> searchCursor = vehicleCollection.find(new BasicDBObject("$and", searchParamList))
				.iterator();

		// MongoCursor<Document> searchCursor =
		// vehicleCollection.find(searchParam).iterator();
		List<String> result = new ArrayList<>();
		while (searchCursor.hasNext()) {
			Document nextResult = searchCursor.next();
			System.out.println(nextResult);
			result.add(nextResult.toJson());
		}
		// MongoCursor<Document> searchCursor =
		// vehicleCollection.find(
		// Filters.eq(fieldName, value)
		//
		//
		// ).iterator();
		//

		return result;

	}

	@Override
	public String deleteVehicleById(String id) {
		// TODO Auto-generated method stub
		System.out.println(id);

		ObjectId oid = new ObjectId(id);
		Document v = vehicleCollection.find(Filters.eq("_id", oid)).first();
		vehicleCollection.deleteOne(v);
		System.out.println(v);

		// vehicleCollection.(Filters.eq("_id",id));

		// if(vehicleCursor.hasNext()){
		// System.out.println();
		// vehicleCollection.findOneAndDelete(vehicleCursor.next());
		// }else{
		// //TODO: its getting here, so its probably not finding stuff
		// System.err.println("Could not be deleted");
		// }
		return null;
	}

	@Override
	public String getVehicleImage(String id) {
		ImageScraper is = new ImageScraper();
		ObjectId oid = new ObjectId(id);
		Document vehicleDocument = vehicleCollection.find(Filters.eq("_id",oid)).projection(include("year","make","model")).first();
		int year = (int) vehicleDocument.get("year");
		String make = vehicleDocument.get("make").toString();
		String model = vehicleDocument.get("model").toString();
		
		String imageLink = is.scrapeStuff(year, make, model);
		System.out.println("DAO: " + imageLink);
		String imageJSON = "{\"imageLink\":\""+imageLink+"\"}";
		return imageJSON;

	}

	@Override
	public String getMechData(String id) {
		System.out.println(id);
		
		ObjectId oid = new ObjectId(id);
		Document mechData = vehicleCollection.find(Filters.eq("_id", oid)).projection(fields(include("mechData"),exclude("_id"))).first();
		System.out.println(mechData.toJson());
		return mechData.toJson();
	}

	@Override
	public String getEpaData(String id) {
		System.out.println(id);
		
		ObjectId oid = new ObjectId(id);
		Document epaData = vehicleCollection.find(Filters.eq("_id", oid)).projection(fields(include("epaData"),exclude("_id"))).first();
		System.out.println(epaData.toJson());
		return epaData.toJson();

	}

	//get lists
	
	@Override
	public List<String> getModelListByMake(String make) {
		MongoCursor<Document> cursor = vehicleCollection.find(Filters.eq("make", Pattern.compile(".*" + make.trim().toLowerCase() + "*.", Pattern.CASE_INSENSITIVE))).iterator();
		List<String> modelList = new ArrayList<>();
		while(cursor.hasNext()){
			Document document = cursor.next();
			String model = document.get("model").toString();
			modelList.add(model);
		}
		return modelList;
	}

	@Override
	public Set<String> getTransmissionTypeList(){
		MongoCursor<Document> resultCursor = vehicleCollection.find().projection(include("mechData.transmissionType")).iterator();
		List<String> resultList = new ArrayList();
		Set<String> resultSet = new TreeSet<>();
		while(resultCursor.hasNext()){
			Document result = resultCursor.next();
			Document mechData = (Document) result.get("mechData");
			String transmissionTypeString =  mechData.get("transmissionType").toString();
			if(!transmissionTypeString.equals("")){
				resultSet.add(mechData.get("transmissionType").toString());
			}
		}
		return resultSet;
		
	}
	
	@Override
	public Set<String> getFuelTypeList(){
		MongoCursor<Document> resultCursor = vehicleCollection.find().projection(include("mechData.fuelType")).iterator();
		List<String> resultList = new ArrayList();
		Set<String> resultSet = new TreeSet<>();
		while(resultCursor.hasNext()){
			Document result = resultCursor.next();
			Document mechData = (Document) result.get("mechData");
			String fuelTypeString =  mechData.get("fuelType").toString();
			if(!fuelTypeString.equals("")){
				resultSet.add(mechData.get("fuelType").toString());
			}
		}
		return resultSet;
		
	}
	
	@Override
	public Set<String> getDriveTypeList(){
		MongoCursor<Document> resultCursor = vehicleCollection.find().projection(include("mechData.driveType")).iterator();
		List<String> resultList = new ArrayList();
		Set<String> resultSet = new TreeSet<>();
		while(resultCursor.hasNext()){
			Document result = resultCursor.next();
			Document mechData = (Document) result.get("mechData");
			String driveTypeString =  mechData.get("driveType").toString();
			if(!driveTypeString.equals("")){
				resultSet.add(mechData.get("driveType").toString());
			}
		}
		return resultSet;
		
	}
	
	
	@Override
	public Set<String> getMakeList(){
		MongoCursor<Document> resultCursor = vehicleCollection.find().projection(include("make")).iterator();
		List<String> resultList = new ArrayList();
		Set<String> resultSet = new TreeSet<>();
		while(resultCursor.hasNext()){
			Document result = resultCursor.next();
			Document mechData = (Document) result.get("mechData");
			String make =  result.get("make").toString();
			if(!make.equals("")){
				resultSet.add(make);
			}
		}
		return resultSet;
		
		
		
	}
	
	@Override
	public String updateVehicle(VehicleFormData vfd) {
		ObjectId oid = new ObjectId(vfd.getId());
		System.out.println(oid);
		Document vehicleToUpdate = vehicleCollection.find(Filters.eq("_id", oid)).first();
		System.out.println("origin: " + vehicleToUpdate);
		Document convertedVehicle = convertFormDataToDocument(vfd);
		System.out.println("new: " + convertedVehicle);

		vehicleCollection.replaceOne(Filters.eq("_id", oid), new Document(convertFormDataToMap(vfd)));

		return "Updated";
	}

	private Document convertFormDataToDocument(VehicleFormData vfd) {
		Map<String, Object> vehicleDataMap = new HashMap<>();

		vehicleDataMap.put("year", vfd.getYear());
		vehicleDataMap.put("make", vfd.getMake().trim());
		vehicleDataMap.put("model", vfd.getModel().trim());
		Map<String, Object> mechDataMap = new HashMap<>();
		mechDataMap.put("driveType", vfd.getDriveType().trim());
		mechDataMap.put("displacement", vfd.getDisplacement());
		mechDataMap.put("transmissionType", vfd.getTransmissionType().trim());
		mechDataMap.put("fuelType", vfd.getFuelType().trim());

		Map<String, Object> epaDataMap = new HashMap<>();
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

	private Map<String, Object> convertFormDataToMap(VehicleFormData vfd) {
		Map<String, Object> vehicleDataMap = new HashMap<>();

		vehicleDataMap.put("year", vfd.getYear());
		vehicleDataMap.put("make", vfd.getMake().trim());
		vehicleDataMap.put("model", vfd.getModel().trim());
		Map<String, Object> mechDataMap = new HashMap<>();
		mechDataMap.put("driveType", vfd.getDriveType().trim());
		mechDataMap.put("displacement", vfd.getDisplacement());
		mechDataMap.put("transmissionType", vfd.getTransmissionType().trim());
		mechDataMap.put("fuelType", vfd.getFuelType().trim());

		Map<String, Object> epaDataMap = new HashMap<>();
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
