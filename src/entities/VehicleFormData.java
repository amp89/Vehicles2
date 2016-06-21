package entities;

public class VehicleFormData {
	
	private String id;
	private int year;
	private String model;
	private String make;
//	private String mechData;
	private String driveType;
	private double displacement;
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
			double highwayMpg) {
		super();
//		this.id = id;
		this.year = year;
		this.model = model;
		this.make = make;
		this.driveType = driveType;
		this.displacement = displacement;
		this.transmissionType = transmissionType;
		this.fuelType = fuelType;
		this.cityMpg = cityMpg;
		this.hasGasTax = hasGasTax;
		this.emissions = emissions;
		this.highwayMpg = highwayMpg;
		this.averageMpg = (highwayMpg + cityMpg) / 2.0;
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
				+ driveType + ", displacement=" + displacement + ", transmissionType=" + transmissionType
				+ ", fuelType=" + fuelType + ", cityMpg=" + cityMpg + ", hasGasTax=" + hasGasTax + ", emissions="
				+ emissions + ", highwayMpg=" + highwayMpg + ", averageMpg=" + averageMpg + "]";
	}
	
	
	
	
	

}
