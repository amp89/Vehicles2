package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.VehiclesDAO;
import entities.EpaData;
import entities.MechData;
import entities.SearchFormVehicleData;
import entities.Vehicle;
import entities.VehicleFormData;

@RestController
public class VehicleRestController {

	@Autowired
	private VehiclesDAO dao;
	
	//TODO test route
	@RequestMapping("/hi")
	public String sayHi(){
		return dao.sayHi();
	}
	
	//TODO test route
	@RequestMapping("/test")
	public List<String> test(){
		return dao.test();
	}
	
	//TODO test route
	@RequestMapping("/testVehicle")
		public Vehicle testVehicle(){
		Vehicle v = new Vehicle();
		v.setMechData(new MechData());
		v.setEpaData(new EpaData());

		return v;
	}
	
	//TODO test route
	@RequestMapping("/testVehcileList")
	public List<Vehicle> testVehicleList(){
		Vehicle v = new Vehicle();
		v.setMechData(new MechData());
		v.setEpaData(new EpaData());
		List<Vehicle> vList = new ArrayList<>();
		vList.add(v);
		vList.add(v);
		return vList;
	}
	
	@RequestMapping(value = "/getOneVehicle/{id}", method = RequestMethod.GET)
	public String getOneVehicle(@PathVariable String id){
		String vehicleJSON = dao.getVehicleById(id);
		System.out.println("sending back " + vehicleJSON);
		return vehicleJSON;
	}
	
	@RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
	public String updateVehicle(@RequestBody VehicleFormData vfd){
		System.out.println(vfd);
		dao.updateVehicle(vfd);
		//TODO add update dao
		return "updated";
	}
	
	@RequestMapping(value = "/testVehiclePost", method = RequestMethod.POST)
	public String testVehiclePost(@RequestBody VehicleFormData vfd){
		System.out.println("in test vehicle POSTTTT");
		System.out.println(vfd);
		//TODO add add v to dao
		return "vehicle added";
	}
	
	@RequestMapping(value="/deleteById/{id}", method = RequestMethod.DELETE)
	public String deleteById(@PathVariable String id){
		dao.deleteVehicleById(id);
		return "vehicle deleted";
	}
	
	
	
	@RequestMapping(value="/searchVehicles", method= RequestMethod.POST)
	public List<String> searchVehicles(@RequestBody SearchFormVehicleData sfvd){
		System.out.println(sfvd);
		List<String> resultList = dao.getVehiclesByParameters(sfvd);
		System.out.println("IN controller, resturning results");
		for (String string : resultList) {
			System.out.println(string);
		}
		
		
		
		return resultList;
		
	}
	
	
	
	@RequestMapping(value="/getModelList/{make}", method= RequestMethod.GET)
	public List<String> getModelListByMake(@PathVariable String make){
	
		return null;
		
	}
	
	
	@RequestMapping(value="/getTransmissionTypeList", method= RequestMethod.GET)
	public Set<String> getTransmissionTypeList(){
		Set<String> result = dao.getTransmissionTypeList();
		
		return result;
	}
	
	
	@RequestMapping(value="/getFuelTypeList", method= RequestMethod.GET)
	public Set<String> getFuelTypeList(){
		Set<String> result = dao.getFuelTypeList();
		return result;
	}
	
	
	@RequestMapping(value="/getDriveTypeList", method= RequestMethod.GET)
	public Set<String> getDriveTypeList(){
		Set<String> result = dao.getDriveTypeList();
		return result;
	}
	
	
	@RequestMapping(value="/getMakeList", method= RequestMethod.GET)
	public Set<String> getMakeList(){
		Set<String> result = dao.getMakeList();
		return result;
	}
	
	//data for individual vehicles
	
	@RequestMapping(value="/getMechData/{id}", method= RequestMethod.GET)
	public String getMechData(@PathVariable String id){
		System.out.println("GETTING MECH DATA FOR: " + id);
		return dao.getMechData(id);
	}
	
	@RequestMapping(value="/getEpaData/{id}", method= RequestMethod.GET)
	public String getEpaData(@PathVariable String id){
		System.out.println("GETTING MECH DATA FOR: " + id);
		return dao.getEpaData(id);
	}
	
	@RequestMapping(value="/getImage/{id}", method= RequestMethod.GET)
	public String getImage(@PathVariable String id){
		String image =  dao.getVehicleImage(id);
		System.out.println("controller: " + image);
		return image;
	}
	
	
	
	
//	@RequestMapping(value="/getAll",method=RequestMethod.GET){
//		
//	}
	
	
}
