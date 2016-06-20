package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleRestController {

	@RequestMapping("/hi")
	public String sayHi(){
		String hi = "{\"response\":\"hi!\"}";
		return hi;
	}
	
}
