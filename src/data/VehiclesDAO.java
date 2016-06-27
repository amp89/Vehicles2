package data;

import java.util.List;
import java.util.Set;

import entities.SearchFormVehicleData;
import entities.VehicleFormData;

public interface VehiclesDAO {


	/*
	 * Get Vehicles
	 */
	public String getVehicleById(String id);
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
