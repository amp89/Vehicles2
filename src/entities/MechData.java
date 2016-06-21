package entities;

public class MechData {
	private String driveType;
	private double displacement;
	private String transmissionType;
	private String fuelType;
	
	public MechData(){
		
	}
	
	
	
	public MechData(String driveType, double displacement, String transmissionType, String fuelType) {
		super();
		this.driveType = driveType;
		this.displacement = displacement;
		this.transmissionType = transmissionType;
		this.fuelType = fuelType;
	}



	public String getDriveType() {
		return driveType;
	}
	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}
	public double getDisplacement() {
		return displacement;
	}
	public void setDisplacement(double displacement) {
		this.displacement = displacement;
	}
	public String getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	
	
	
	
	
	
	
}
