package databaseLoad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class LoadFromCSV {
	//TODO vehicle test, switch to vehicle name
	final static String DB_NAME = "vehicleTestTwo";
    final static String DB_ADDRESS = "mongodb://localhost:27017";
    final static String CSV_FILE_NAME = "vehicle-specifications.csv";
    
    static MongoClientURI dbURL = new MongoClientURI(DB_ADDRESS);
    static MongoClient mongoClient = new MongoClient(dbURL);
    static MongoDatabase db = mongoClient.getDatabase(DB_NAME);
    static MongoCollection<Document> vehicleCollection = db.getCollection("newVehicles");
  
    
    public static void main(String[] args) {

        BufferedReader buf = null;
        
        try{
        	buf = new BufferedReader(new FileReader(CSV_FILE_NAME));
        	String line = "";
        	while((line = buf.readLine()) != null){
        		System.out.println(line);
        		String[] tokens = line.trim().split(",");
        		//throw away crap data
        		try{
        			int year = Integer.parseInt(tokens[1].trim());
        			String make = tokens[2].trim();
        			String model = tokens[3];
        			double highwayMpg = Double.parseDouble(tokens[4].trim());
        			double cityMpg = Double.parseDouble(tokens[5].trim());
        			String fuelType = tokens[6].trim();
        
        			String[] transmissionTypeAndGears = tokens[7].trim().split(" ");
        			String transmissionType = transmissionTypeAndGears[0].trim();
        			if(transmissionType.toLowerCase().contains("auto")){
        				transmissionType = "Automatic";
        			}else if(transmissionType.toLowerCase().contains("man")){
        				
        				transmissionType = "Manual";
        			}else{
        				transmissionType = "Other";
        				
        			}
        			String driveType = tokens[8].trim();
        			int cylinders = Integer.parseInt(tokens[9].trim());
        			double displacement = Double.parseDouble(tokens[10].trim());
        			String gasTaxRaw = tokens[11].trim();
        			boolean hasGasTax;
        			if(gasTaxRaw.equals("")){
        				hasGasTax = false;
        			}else if(gasTaxRaw.toLowerCase().equals("t")){
        				hasGasTax = true;
        			}else{
        				throw new NumberFormatException("Data format problem - trash this row and continue");
        			}
        			double emissions = Double.parseDouble(tokens[12].trim());
        			
        			double averageMpg = (highwayMpg + cityMpg) / 2.0;
        			
        			//add a new car to the database
        			addToMongo(year, make, model, highwayMpg, cityMpg, fuelType, transmissionType, driveType, cylinders, displacement, hasGasTax, emissions, averageMpg);
        			
        			
        			 
        		}catch(NumberFormatException nfe){
        			System.err.println(nfe);
        		}
//        		string 
        		
        	}
        	
        	
        }catch(IOException e){
        	System.out.println(e);
        }
        
        
	}
    
    private static void addToMongo(int year, String make, String model, double highwayMpg,
			double cityMpg, String fuelType, String transmissionType, 
			String driveType, int cylinders, double displacement,
			boolean hasGasTax, double emissions, double averageMpg){
    		//vehicleCollection
    	//make vehicle map
    	Map<String,Object> vehicleDataMap = new HashMap<>();
    	
    	vehicleDataMap.put("year", year);
    	vehicleDataMap.put("make",make);
    	vehicleDataMap.put("model",model);
    	
    	Map<String,Object> epaDataMap = new HashMap<>();
    	
    	epaDataMap.put("highwayMpg", highwayMpg);
    	epaDataMap.put("cityMpg", cityMpg);
    	epaDataMap.put("hasGasTax", hasGasTax);
    	epaDataMap.put("emissions", emissions);
    	epaDataMap.put("averageMpg",averageMpg);
    	
    	Map<String,Object> mechDataMap = new HashMap<>();
    	
    	mechDataMap.put("fuelType", fuelType);
    	mechDataMap.put("cylinders", cylinders);
    	mechDataMap.put("displacment", displacement);
    	mechDataMap.put("transmissionType", transmissionType);
    	mechDataMap.put("driveType", driveType);
    	
    	vehicleDataMap.put("epaData",epaDataMap);
    	vehicleDataMap.put("mechData", mechDataMap);
    	
    	Document vehicleData = new Document(vehicleDataMap);
    	
    	System.out.println(vehicleData);
    	
    	vehicleCollection.insertOne(vehicleData);
       
    }
    
    
	
}
