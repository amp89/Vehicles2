package entities;

public class VehicleFormData {
	
	private String id;
	private int year;
	private String model;
	private String make;
//	private String mechData;
	private String driveType;
	private double displacment;
	private int cylinders;
	private String transmissionType;
	private String fuelType;
//	private EpaData epaData;
	private double cityMpg;
	private boolean hasGasTax;
	private double emissions;
	private double highwayMpg;
	private double averageMpg;
	
	public VehicleFormData(){
		
	}

	public VehicleFormData(int year, String model, String make, String driveType, double displacement,
			String transmissionType, String fuelType, double cityMpg, boolean hasGasTax, double emissions,
			double highwayMpg, int cylinders) {
		super();
//		this.id = id;
		this.year = year;
		this.model = model;
		this.make = make;
		this.driveType = driveType;
		this.displacment = displacement;
		this.transmissionType = transmissionType;
		this.fuelType = fuelType;
		this.cityMpg = cityMpg;
		this.hasGasTax = hasGasTax;
		this.emissions = emissions;
		this.highwayMpg = highwayMpg;
		this.averageMpg = (highwayMpg + cityMpg) / 2.0;
		this.cylinders = cylinders;
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

	public String getDriveType() {
		return driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	public double getDisplacment() {
		return displacment;
	}

	public void setDisplacment(double displacement) {
		this.displacment = displacement;
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

	public double getCityMpg() {
		return cityMpg;
	}

	public void setCityMpg(double cityMpg) {
		this.cityMpg = cityMpg;
	}

	public boolean isHasGasTax() {
		return hasGasTax;
	}

	public void setHasGasTax(boolean hasGasTax) {
		this.hasGasTax = hasGasTax;
	}

	public double getEmissions() {
		return emissions;
	}

	public void setEmissions(double emissions) {
		this.emissions = emissions;
	}

	public double getHighwayMpg() {
		return highwayMpg;
	}

	public void setHigwayMpg(double higwayMpg) {
		this.highwayMpg = higwayMpg;
	}

	public double getAverageMpg() {
		return averageMpg;
	}

	public void setAverageMpg(double averageMpg) {
		this.averageMpg = averageMpg;
	}

	@Override
	public String toString() {
		return "VehicleFormData [id=" + id + ", year=" + year + ", model=" + model + ", make=" + make + ", driveType="
				+ driveType + ", displacement=" + displacment + ", transmissionType=" + transmissionType
				+ ", fuelType=" + fuelType + ", cityMpg=" + cityMpg + ", hasGasTax=" + hasGasTax + ", emissions="
				+ emissions + ", highwayMpg=" + highwayMpg + ", averageMpg=" + averageMpg + "]";
	}

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}
	
	
	
	
	

}
