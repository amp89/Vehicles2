package entities;

public class Vehicle {

	private String id;
	private int year;
	private String model;
	private String make;
	private MechData mechData;
	private EpaData epaData;
	
	public Vehicle(){
		
	}
	
	
	
	public Vehicle(String id, int year, String model, String make, MechData mechData, EpaData epaData) {
		super();
		this.id = id;
		this.year = year;
		this.model = model;
		this.make = make;
		this.mechData = mechData;
		this.epaData = epaData;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public MechData getMechData() {
		return mechData;
	}
	public void setMechData(MechData mechData) {
		this.mechData = mechData;
	}
	public EpaData getEpaData() {
		return epaData;
	}
	public void setEpaData(EpaData epaData) {
		this.epaData = epaData;
	}
	
	
	
}
