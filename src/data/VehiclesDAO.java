package data;

import java.util.List;

public interface VehiclesDAO {

	/*
	 * Test Methods
	 */
	public String sayHi();
	
	//TODO TEST
//	public void connect();
//	
//	public void disconnect();
//	
//	]
	public List<String> test();
	
	/*
	 * Get Vehicles
	 */
	public String getVehicles();
	public String getVehiclesById();
	public String getVehiclesByYearRange();
	public String getVehiclesByMake();
	public String getVehiclesByModel();
	public String getVehiclesByDriveType();
	public String getVehiclesByDisplacementRange();
	public String getVehiclesByTransmission();
	public String getVehiclesByCylinderRange();
	public String getVehiclesByCylinders();
	public String getVehiclesByFuelType();
	public String getVehiclesByMpgRange();
	public String getVehiclesByEmissionsRange();
	public String getVehiclesByGasTax();
	//genneral
	public String getVehiclesByParameters();
	/*
	 * Delete vehicles by id
	 */
	
	public String deleteVehicleById(String id);
	
	/*
	 * get set of attributes
	 */
	public String getVehicleImage();
	public String getMechData();
	public String getEpaData();
	
	/*
	 * get model list by make
	 */
	public String getModelListByMake();
	
	/*
	 * 
	 */
	
	/*
	 * 
	 */
	
	/*
	 * 
	 */
	
	/*
	 * 
	 */
	
	
	
}
