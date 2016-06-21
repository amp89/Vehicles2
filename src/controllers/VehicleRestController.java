package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.VehiclesDAO;
import entities.EpaData;
import entities.MechData;
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
	
	@RequestMapping(value = "/testVehiclePost", method = RequestMethod.POST)
	public String testVehiclePost(@RequestBody VehicleFormData vfd){
		System.out.println("in test vehicle POSTTTT");
		System.out.println(vfd);
		return "vehicle added";
	}
	
	@RequestMapping(value="/deleteById/{id}", method = RequestMethod.DELETE)
	public String deleteById(@PathVariable String id){
		System.out.println("got to delete method");
		dao.deleteVehicleById(id);
		return "vehicle deleted";
	}
	
	
//	@RequestMapping(value="/getAll",method=RequestMethod.GET){
//		
//	}
	
	
}
