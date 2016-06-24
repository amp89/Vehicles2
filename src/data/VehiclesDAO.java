package data;

import java.util.List;
import java.util.Set;

import entities.SearchFormVehicleData;
import entities.VehicleFormData;

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
	public String getVehicleById(String id);
	public String getVehiclesById(String id);
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
	public List<String> getVehiclesByParameters(SearchFormVehicleData sfvd);
	/*
	 * Delete vehicles by id
	 */
	
	public String deleteVehicleById(String id);
	
	/*
	 * get set of attributes
	 */
	public String getVehicleImage(String id);
	public String getMechData(String id);
	public String getEpaData(String id);
	
	/*
	 * get model list by make
	 */
	public Set<String> getModelListByMake(String make);
	public Set<String> getTransmissionTypeList();
	public Set<String> getFuelTypeList();
	public Set<String> getDriveTypeList();
	public Set<String> getMakeList();
	
	/*
	 * update vehicle
	 */
	public String updateVehicle(VehicleFormData vfd);
	public String addVehicle(VehicleFormData vfd);
	
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
